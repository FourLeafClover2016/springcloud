package com.hwx.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Huawei Xie
 * @date: 2019/12/28
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 注解@LoadBalanced为RestTemplate整合Ribbon
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
