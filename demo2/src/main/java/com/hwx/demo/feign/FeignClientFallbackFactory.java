package com.hwx.demo.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * feign 接口FallbackFactory类，
 */
@Component
public class FeignClientFallbackFactory implements FallbackFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);
    @Override
    public Object create(Throwable throwable) {
        return new Demo1Client() {
            @Override
            public String test() {
                FeignClientFallbackFactory.LOGGER.info("fallback", "reason was:", throwable);
                if (throwable instanceof RuntimeException) {
                    return "Demo1Client 调用失败，返回异常处理结果RuntimeException";
                } else {
                    return "Demo1Client 调用失败，返回异常处理结果Exception";
                }

            }
        };
    }
}
