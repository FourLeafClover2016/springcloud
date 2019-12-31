package com.hwx.demo.feign;

import org.springframework.stereotype.Component;

/**
 * Demo1Client 远程调用失败处理类
 */
@Component
public class FeignClientFallback implements Demo1Client{
    @Override
    public String test() {
        return "Demo1Client 调用失败，返回异常处理结果";
    }
}
