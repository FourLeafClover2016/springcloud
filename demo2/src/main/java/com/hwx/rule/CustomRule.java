package com.hwx.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义ribbon的规则
 *
 * @author: Huawei Xie
 * @date: 2019/12/28
 */
@Configuration
public class CustomRule {
    /**
     * 该类为Ribbon的配置类
     * 该类不能被@ComponentScan扫描到，否则该类的配置信息将会被所有ribbon client共享
     */
    @Bean
    public IRule ribbonRule() {
        // 负载均衡规则，改为随机
        return new RandomRule();
    }
}
