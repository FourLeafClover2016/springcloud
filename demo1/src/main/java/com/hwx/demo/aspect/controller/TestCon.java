package com.hwx.demo.aspect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCon {
    @GetMapping("/getstr")
    public Object get(){
        return "pp";
    }
}
