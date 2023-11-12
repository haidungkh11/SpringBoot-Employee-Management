package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger myLogger = Logger.getLogger(getClass().getName());


    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}
    @Pointcut("execution( * com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){}
    @Pointcut("execution( * com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.security.*.*(..))")
    private void forSecurityPackage(){}
    @Pointcut("forControllerPackage() || forDaoPackage() || forSecurityPackage() || forServicePackage()")
    private  void forAppFlow(){}


    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("========>>>> calling method: "+ theMethod);
    }
    @AfterReturning(pointcut = "forAppFlow()",returning = "theResult")
    public  void after(JoinPoint theJoinPoint, Object theResult){
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("========>>>> calling method: "+ theMethod);
        myLogger.info("========>>>> calling object "+ theResult);
    }
}
