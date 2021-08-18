package com.lin.jx3_bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author linyanhao on 2021/8/18.
 * @version 1.0
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
