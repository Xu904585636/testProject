package com.kingleadsw.ysm.datascource;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Auther: zhoujie
 * @Date: 2018/11/1 10:21
 * @Description:
 */

@MapperScan({"com.kingleadsw.ysm.dao*"})
@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
public class MyBatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");

        return page;
    }


}
