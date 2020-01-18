package com.kingleadsw.ysm.odp.controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Configuration
@SpringBootApplication(scanBasePackages = "com.kingleadsw.*")
public class ODPApplication {

	public static void main(String[] args) {
		SpringApplication.run(ODPApplication.class, args);
	}



	@Bean
    RestTemplate restTemplate(){

		return  new RestTemplate();
	}

	/**
	 * 视频加密后的ID存放日期
	 */
	@Bean
	public CopyOnWriteArrayList encryptQueue(){
		return new CopyOnWriteArrayList<String>();
	}
}
