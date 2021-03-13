package com.intellie.zuul.core.config;

import com.intellie.zuul.filters.AccessFilter;
import com.intellie.zuul.filters.PermissionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/14 20:51
 */
@Configuration
public class FilterConf {
    @Bean
    public AccessFilter filter1() {
        return new AccessFilter();
    }

    @Bean
    public PermissionFilter filter3() {
        return new PermissionFilter();
    }
}
