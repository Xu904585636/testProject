package com.kingleadsw.ysm.mtp.controller.config;

import com.kingleadsw.ysm.interceptor.EventLogInterceptor;
import com.kingleadsw.ysm.interceptor.RepeatRequestInterceptor;
import com.kingleadsw.ysm.interceptor.TokenMtpAuthenticationInterceptor;
import com.kingleadsw.ysm.need.token.TokenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author  zhoujie
 *加载拦截器
 */
@Configuration
@Log4j2
public class MtpConfig extends WebMvcConfigurerAdapter {

    @Autowired
    TokenService tokenService;


    @Bean
    public RepeatRequestInterceptor repeatRequestInterceptor() {
        return new RepeatRequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new EventLogInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger");
        registry.addInterceptor(new TokenMtpAuthenticationInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger", "/login");
        registry.addInterceptor(repeatRequestInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger");

        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/restdoc/**").addResourceLocations("classpath:/swagger-dist/");
    }

}
