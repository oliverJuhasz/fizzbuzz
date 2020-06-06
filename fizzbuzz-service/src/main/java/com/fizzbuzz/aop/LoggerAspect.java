package com.fizzbuzz.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@Aspect
public class LoggerAspect {


    @Pointcut("within(com.fizzbuzz.service..*)")
    protected void methodsInService() {}

    @Around("methodsInService()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long stopTotalTime = System.currentTimeMillis() - startTime;
        StringBuilder stringBuilder = new StringBuilder("Parameter passed: ");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                stringBuilder.append(arg.toString());
            }
        } else {
            stringBuilder.append("none");
        }
        stringBuilder.append("\nReturn value: ").append(returnValue == null ? "No return value" : returnValue).append("\nExecution time: ")
                .append(stopTotalTime);
        log.info(stringBuilder.toString());
        return returnValue;
    }
    
    @AfterThrowing(pointcut="within(com.fizzbuzz.service..*)", throwing="ex")
    public void logException(Exception ex) {
        log.error("Exception occured at" + LocalDateTime.now().toString() +": " + ex);
    }
}
