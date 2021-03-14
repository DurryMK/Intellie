package com.intellie.data.core.aspect;

import com.intellie.common.entity.system.CacheTag;
import com.intellie.common.entity.system.Emap;
import com.intellie.common.entity.user.User;
import com.intellie.common.utils.StringUtil;
import com.intellie.data.provider.service.common.CommonService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/13 11:02
 * @describe:
 */
@Component
public class DataPermission {
    private Logger logger = LoggerFactory.getLogger(DataPermission.class);

    @Value("${data.isGateway}")
    private int isGateway;//是否开启网关的身份识别

    @Autowired
    private CommonService commonService;

    @Pointcut("execution(* com.intellie.data.export..*.*(..))")
    private void PermissionAccess() {
    }

    @Around("PermissionAccess()")
    public Object process(ProceedingJoinPoint joinPoint) {
        Emap em = new Emap();
        try {
            HttpServletRequest request = this.initRequest(joinPoint);
            if (request == null) {
                return em.fail("请求异常");
            }
            logger.debug("Method=>{}, Origin => {}, SessionId => {},URI => {}",
                    request.getMethod(), request.getRequestURL(),request.getSession().getId(),request.getRequestURI());
            if(isGateway == 0){
                String parameter = request.getParameter(CacheTag.GATEWAY_TOKEN);
                if(StringUtil.isNull(parameter))
                    return em.invalid();
            }
            //只放行已登录的请求
            User loginStatus = commonService.getLoginStatus(request);
            if(loginStatus == null)
                return em.invalid();
            return joinPoint.proceed();
        } catch (Throwable e) {
            logger.error("【切面处理异常】:{}", e);
            return em.fail("系统异常");
        }
    }
    private HttpServletRequest initRequest(ProceedingJoinPoint joinPoint){
        //获取方法参数
        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            if (o instanceof HttpServletRequest) {
                //获取请求内容
                return (HttpServletRequest) o;
            }
        }
        return null;
    }
}
