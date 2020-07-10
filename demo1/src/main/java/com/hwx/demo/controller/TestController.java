package com.hwx.demo.controller;

import com.hwx.demo.aspect.annotation.ApiLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Huawei Xie
 * @date: 2019/12/28
 */
@RestController
public class TestController {
    @GetMapping("/test")
    @ApiLog
    public String test() {
        return "demo1 success";
    }
}
