package com.example.distribution.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // 跨域配置
//    @Configuration
//    public static class CorsConfig implements WebMvcConfigurer {
//
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**")
//                    .allowedOrigins("*")
//                    .allowedMethods("GET", "POST", "PUT", "DELETE")
//                    .allowedHeaders("*")
//                    .allowCredentials(true)
//                    .maxAge(3600);
//        }
//    }
    /**
     * HTTP验证规则
     *
     * @param http h
     * @throws Exception e
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //开启跨域
        http.csrf().disable().cors();

        //禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(new JwtAuthorizationFilter(authenticationManagerBean()))
//                .csrf().disable();
        //添加自定义的jwt过滤器
        http.addFilter(new JwtAuthorizationFilter(authenticationManagerBean()));

    }

    /**
     * SpringSecurity有默认的跨域配置 会无法放行RequestHeader带有"Authorization"请求
     * 防止前端请求api报出cors error
     *
     * @return *
     */
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedHeader("DELETE");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addAllowedOrigin("*");
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }

}
