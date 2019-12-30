package com.hwx.demo.feign;

import org.springframework.web.bind.annotation.GetMapping;

public interface UserFeignClient {
    @GetMapping("/demo1/getUser")
    String getCurrentUsername();

}
