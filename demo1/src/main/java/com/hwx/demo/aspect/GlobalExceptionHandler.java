package com.hwx.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.hwx.demo.entity.SecurityUser;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(MyException.class)
    public JSONObject bizExceptionHandler(HttpServletRequest req){

        JSONObject sysUser = new JSONObject();
        sysUser.put("code","1");
        System.out.println("-------------------");
        return sysUser;
    }
}

