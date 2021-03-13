package com.intellie.zuul.filters;

import com.alibaba.fastjson.JSONObject;
import com.intellie.common.entity.system.CacheTag;
import com.intellie.common.entity.system.RES;
import com.intellie.zuul.utils.RSAUtil;
import com.intellie.zuul.utils.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/18 20:41
 */
public class PermissionFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(PermissionFilter.class);

    @Autowired
    private RedisUtil redisUtil;

    private final int LEVEL = 2;

    private final String AUTHOR = "authorization";

    @Value("${custom.api.token.overtime}")
    private long tokenOverTime;//请求失效时间

    @Value("${custom.api.list.overtime}")
    private long listOverTime;//列表失效时间

    @Value("${custom.api.token.split}")
    private String SPLIT;//签名分隔符

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
        return (boolean) RequestContext.getCurrentContext().get(CacheTag.NEXT_INTERCEPT);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        JSONObject backJson = new JSONObject();

        //登录状态验证
        boolean loginAccess = loginVerify(request);
        //API签名验证
        boolean tokenAccess = apiVerify(request);

        if (!loginAccess) {
            backJson.put(RES.TOKEN, RES.FAIL);
            backJson.put(RES.INFO, "未登录");
            //终止路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            ctx.setResponseBody(backJson.toString());
            return null;
        }

        if (!tokenAccess) {
            backJson.put(RES.TOKEN, RES.FAIL);
            backJson.put(RES.INFO, "无效的请求");
            //终止路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            ctx.setResponseBody(backJson.toString());
            return null;
        }
        return null;
    }

    private boolean apiVerify(HttpServletRequest request) {
        try {
            //请求头中必须含有Authorization
            if (request.getHeader(AUTHOR) == null || StringUtils.isEmpty(request.getHeader(AUTHOR)))
                return false;
            //获取签名
            String author = request.getHeader(AUTHOR);
            //获取session中的私钥
            String privateKey = (String) request.getSession().getAttribute(CacheTag.PRIVATE);
            //对签名解密
            author = RSAUtil.decrypt(author, privateKey).replaceAll("\"", "");
            //签名不为空且必须含有分隔符
            if (StringUtils.isNotEmpty(author) && author.contains(SPLIT)) {
                //签名结构 请求发出时间;唯一标识符
                String[] split = author.split(SPLIT);
                String startTime = split[0];
                String auId = split[1];
                //当前时间与请求时间的时间差
                long l = System.currentTimeMillis() - Long.parseLong(startTime);
                if (l > tokenOverTime) {
                    //超时
                    return false;
                }
                //获取用户的token列表名称
                String tokenListName = request.getSession().getAttribute(CacheTag.API_LIST_NAME) + "";
                if (StringUtils.isEmpty(tokenListName)) {
                    return false;
                }
                if (redisUtil.hasKey(tokenListName)) {
                    //获取redis保存的签名标识符集合 -1代表返回所有
                    List<String> auList = redisUtil.lRange(tokenListName, 0, -1);
                    //遍历比较 ， 如果有相同的则代表是旧的请求
                    for (String au : auList) {
                        if (auId.equals(au)) {
                            return false;
                        }
                    }
                    //请求通过 auId添加到已失效列表
                    redisUtil.lRightPush(tokenListName, auId);
                } else {
                    //如果没有列表就新建
                    List<String> auList = new ArrayList<>();
                    auList.add(auId);
                    redisUtil.lRightPushAll(tokenListName, auList);
                    //设置列表的过期时间
                    redisUtil.expire(tokenListName, listOverTime, TimeUnit.MILLISECONDS);
                    request.getSession().setAttribute(CacheTag.API_LIST_NAME, tokenListName);
                }
                return true;
            }
        } catch (Exception e) {
            log.error("API签名验证异常 ========> {}", e);
        }
        return false;
    }

    private boolean loginVerify(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(CacheTag.SERVER_LOGIN_TOKEN);
        if (attribute != null) {
            return true;
        }
        return false;
    }
}
