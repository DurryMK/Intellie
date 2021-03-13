package com.intellie.zuul.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class CookieConf {
    /**
     * 此配置项用于解决本地开发过程中
     * 非HTTPS连接在跨域请求时不能set-cookie的问题
     * 同时本地谷歌浏览器访问chrome://flags/，搜索SameSite并全部设置为disabled
     */
    @Bean
    public CookieSerializer httpSessionIdResolver() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setUseHttpOnlyCookie(false);
        cookieSerializer.setSameSite("None");
        cookieSerializer.setUseSecureCookie(false);
        return cookieSerializer;
    }
}
