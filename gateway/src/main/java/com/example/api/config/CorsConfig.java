package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.WebFilter;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;


/**
 * 跨域配置
 *
 * @author zc
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        // 允许携带cookie的地址进行跨域
        config.setAllowCredentials(true);

        // 明确列出允许使用凭证的原始来源
        config.setAllowedOrigins(Arrays.asList("http://localhost:8083", "http://localhost:8082", "http://localhost:8084"
                , "http://localhost:8085", "http://localhost:8086"));
        // 或者使用allowedOriginPatterns
        // config.setAllowedOriginPatterns(Arrays.asList("http://example*.com"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}