package com.hwx.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Huawei Xie
 * @date: 2019/12/28
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        String result = restTemplate.getForEntity("http://spring-cloud-demo1/demo1/test", String.class).getBody();
        return result + "--demo2 success";
    }
}
