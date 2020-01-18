package com.kingleadsw.ysm.odp.controller.config;
import com.kingleadsw.ysm.interceptor.EventLogInterceptor;
import com.kingleadsw.ysm.interceptor.RepeatRequestInterceptor;
import com.kingleadsw.ysm.interceptor.TokenOdpAuthenticationInterceptor;
import com.kingleadsw.ysm.need.token.TokenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author  zhoujie
 *
 */

@Configuration
@Log4j2
public class OdpConfig extends WebMvcConfigurerAdapter {

    @Autowired
    TokenService tokenService;

    @Bean
    public RepeatRequestInterceptor repeatRequestInterceptor() {
        return new RepeatRequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new EventLogInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger");
        registry.addInterceptor(new TokenOdpAuthenticationInterceptor(tokenService)).addPathPatterns("/**").excludePathPatterns("/swagger", "/login");
        registry.addInterceptor(repeatRequestInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger");

        super.addInterceptors(registry);
    }



}
