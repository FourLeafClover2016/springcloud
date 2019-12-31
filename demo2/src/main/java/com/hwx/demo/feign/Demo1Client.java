package com.hwx.demo.feign;

import com.hwx.demo.config.FeignLogConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * feign调用接口 fallback = FeignClientFallback.class，调用发生失败后进行回滚处理
 * 也可使用fallbackFactory 产看异常原因，根据不同的原因进行不同的回滚操作
 */
//@FeignClient(name = "spring-cloud-demo1", fallback = FeignClientFallback.class)
@FeignClient(name = "spring-cloud-demo1", fallbackFactory = FeignClientFallbackFactory.class)
public interface Demo1Client {
    @GetMapping("/demo1/test")
    String test();
}
