package com.hwx.demo.feign;

import com.hwx.demo.config.FeignLogConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "spring-cloud-demo1")
public interface Demo1Client {
    @GetMapping("/demo1/test")
    String test();
}
