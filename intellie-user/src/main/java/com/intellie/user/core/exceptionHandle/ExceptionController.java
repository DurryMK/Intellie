package com.intellie.user.core.exceptionHandle;

import com.intellie.common.entity.system.Emap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/8 14:31
 * <p>
 * 统一异常处理
 */

@ControllerAdvice
public class ExceptionController {

    private Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map globalException(HttpServletResponse response, HttpServletRequest request, Exception ex) {
        Emap em = new Emap();
        String URL = request.getRequestURL().toString();
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return em.exception("不支持的访问类型");
        }
        if (ex instanceof NoSuchAlgorithmException) {
            return em.exception("系统异常");
        }
        log.debug("[Controller ERROR] : " + URL);
        log.debug("Error Code：[" + response.getStatus() + "]");
        log.error("Error Info ======> ", ex);
        return em.exception();
    }
}
