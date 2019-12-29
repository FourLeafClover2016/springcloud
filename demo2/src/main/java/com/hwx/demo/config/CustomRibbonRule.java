package com.hwx.demo.config;

import com.hwx.rule.CustomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用RibbonClient，为特定name的Ribbon Client自定义配置
 * 使用@RibbonClient的configuration属性，指定Ribbon的配置类
 *
 * @author: Huawei Xie
 * @date: 2019/12/28
 */
@Configuration
@RibbonClient(name = "spring-cloud-demo1", configuration = CustomRule.class)
public class CustomRibbonRule {
}
