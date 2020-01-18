package com.kingleadsw.ysm.filters;

import com.kingleadsw.ysm.http.EventLogJackson2HttpMessageConverter;
import com.kingleadsw.ysm.props.ApplicationProps;
import com.kingleadsw.ysm.utils.Jsons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class WebConfig
{
    @Autowired
    ApplicationProps applicationProps;


    @Bean
    public FilterRegistrationBean requestFilter()
    {

        FilterRegistrationBean registration = new FilterRegistrationBean();

       registration.setFilter(new RequestFilter(this.applicationProps.getApplicationName()));

        registration.addUrlPatterns(new String[] { "/*" });

        registration.setName("RequestFilter");

        registration.setOrder(3);
        return registration;

    }

    @Bean
    public FilterRegistrationBean corsFilter()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(new CorsFilter());

        registration.addUrlPatterns(new String[] { "/*" });

        registration.setName("CorsFilter");

        registration.setOrder(4);
        return registration;
    }

    @Bean
    public HttpMessageConverters jacksonJsonHttpMessageConverters()
    {
        MappingJackson2HttpMessageConverter jsonConverter = new EventLogJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(Jsons.getObjectMapper());
        HttpMessageConverter<?> converter = jsonConverter;
        return new HttpMessageConverters(new HttpMessageConverter[] { converter });
    }
}