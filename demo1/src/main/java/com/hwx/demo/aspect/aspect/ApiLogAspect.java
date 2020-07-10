package com.hwx.demo.aspect.aspect;

import com.hwx.demo.aspect.MyException;
import com.hwx.demo.aspect.annotation.ApiLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiLogAspect {
    @Before("@annotation(apiLog)")
    public Object before(ApiLog apiLog) throws MyException {
        System.out.println("----------------日志切面");
        throw new MyException();
        //return point.proceed();
    }

}
