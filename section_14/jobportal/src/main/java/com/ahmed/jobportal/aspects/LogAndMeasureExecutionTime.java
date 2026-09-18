package com.ahmed.jobportal.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LogAndMeasureExecutionTime {

    // we can make an annotation and use it
    // @Around("@annotation(com.ahmed.jobportal.aspects.LogAspect)")

    @Around("execution(* com.ahmed.jobportal..*.*(..))")
    public Object logAndMeasureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        // before the method

        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("-> Entering method: {}", methodName);
        log.info("*  Arguments: {}", Arrays.toString(args));

        // after the method

        // execute the method
        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;

        log.info("-> Method executed successful: {}", methodName);
        log.info("*  Execution Time: {} ms", executionTime);

        return result;
    }


}
