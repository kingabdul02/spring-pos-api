package com.king.springposapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class CorsConfiguration {
    @Bean
    public DelegatingFilterProxy corsFilter() {
        return new DelegatingFilterProxy(new CorsFilter());
    }
}

