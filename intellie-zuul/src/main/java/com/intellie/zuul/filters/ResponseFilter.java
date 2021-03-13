package com.intellie.zuul.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RefreshScope
public class ResponseFilter implements Filter {
    /**
     *返回结果字符集设置
     */

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        //统一设置返回字符编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;cahrset=UTF-8");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
