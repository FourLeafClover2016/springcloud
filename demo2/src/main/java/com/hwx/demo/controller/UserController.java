package com.hwx.demo.controller;

import com.hwx.demo.feign.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手动创建Feign，FeignClientsConfiguration是springCloud为feign默认提供的配置类
 * 手动创建Feign需要在feign的接口上取出@FeignClient注解，取出启动类上的@EnableFeignClient
 */
//@Import(FeignClientsConfiguration.class)
//@RestController
public class UserController {
    private UserFeignClient adminClient;

    @Autowired
    public UserController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.adminClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "passwd"))
                .target(UserFeignClient.class, "http://spring-cloud-demo1/");
    }

    @GetMapping("/getUser")
    public String getUser() {
        return this.adminClient.getCurrentUsername();
    }

}
