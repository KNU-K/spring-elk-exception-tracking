package com.example.springelkexceptiontracking.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.MDC;

import java.util.UUID;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private static final String REQUEST_ID = "requestId";

    @Around("execution(* com.example.springelkexceptiontracking.controller..*(..))")
    public Object logControllerExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "Controller");
    }

    @Around("execution(* com.example.springelkexceptiontracking.service..*(..))")
    public Object logServiceExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "Service");
    }

    @Around("execution(* com.example.springelkexceptiontracking.repository..*(..))")
    public Object logRepositoryExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "Repository");
    }

    private Object logExecution(ProceedingJoinPoint joinPoint, String layer) throws Throwable {
        String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestId);

        // Log method start
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        log.info("RequestID: {} | {} execution started: {} with arguments = {}", requestId, layer, methodName, args);

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        // Log method end with execution time
        log.info("RequestID: {} | {} execution finished: {} with result = {} - Duration: {} ms",
                requestId, layer, methodName, result, duration);

        MDC.remove(REQUEST_ID);
        return result;
    }

    @Around("execution(* com.example.springelkexceptiontracking..*(..))")
    public Object logErrors(ProceedingJoinPoint joinPoint) throws Throwable {
        String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestId);
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("RequestID: {} | Error in method: {} | Message: {} | StackTrace: {}", requestId, joinPoint.getSignature(), e.getMessage(), e);
            throw e;
        } finally {
            MDC.remove(REQUEST_ID);
        }
    }
}
