package com.hwx.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "spring-cloud-demo1")
public interface Demo1Client {
    @GetMapping("/demo1/test")
    String test();
}
