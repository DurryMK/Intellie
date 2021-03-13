package com.intellie.zuul.filters;

import com.alibaba.fastjson.JSONObject;
import com.intellie.common.entity.system.CacheTag;
import com.intellie.common.entity.system.RES;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/14 20:41
 */

public class AccessFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(AccessFilter.class);

    private final int LEVEL = 0;

    private final String OPTIONS = "OPTIONS";

    private final String LoginToken = "LOGINSUCCESS";

    private final String HEA = "/auth/hea/";//维护心跳的路径

    private final String LOGIN_METHOD = "/auth/access";//登录路径

    private final String SEND_VCODE = "/sms/";//短信发送路径

    @Value("${custom.env}")
    private String env;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return LEVEL;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 在session中加入token
     * 每次在session中加入token之后在下游服务器并不能实时获取到
     * 因此逻辑改为：
     * 每次登录时，在session加入token，登录服务器不做token校验
     */

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        JSONObject backJson = new JSONObject();
        String URL = request.getRequestURL().toString();
        log.debug("[{}] Method=>{} Origin => {} SessionId ===> {}", this.getClass().getName(),
                request.getMethod(), URL, request.getSession().getId());

        //放行预处理请求
        if (OPTIONS.equals(request.getMethod())) {
            log.debug("预处理请求 ===> {}", request.getRequestURL());
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            //设置为false 后续过滤器将不会执行
            ctx.set(CacheTag.NEXT_INTERCEPT, false);
            return null;
        }

        //放行发送验证码的请求
        if (URL.contains(SEND_VCODE)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            ctx.set(CacheTag.NEXT_INTERCEPT, false);
            return null;
        }

        //放行登录请求
        if (URL.contains(LOGIN_METHOD)) {
            Object attribute = request.getSession().getAttribute(CacheTag.SERVER_LOGIN_TOKEN);
            ctx.set(CacheTag.NEXT_INTERCEPT, false);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            if (attribute != null) {
                //已经登录 直接返回
                backJson.put(RES.TOKEN, RES.SUCCESS);
                backJson.put(RES.INFO, request.getSession().getAttribute(CacheTag.PUBLIC));
                //终止路由
                ctx.setSendZuulResponse(false);
                ctx.setResponseBody(backJson.toString());
            } else {
                //未登录用户的登录请求放行
                ctx.setSendZuulResponse(true);
            }
            return null;
        }

        //放行维护心跳的请求
        if (URL.contains(HEA)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set(CacheTag.NEXT_INTERCEPT, false);
            return null;
        }

        ctx.set(CacheTag.NEXT_INTERCEPT, true);
        return null;
    }
}
