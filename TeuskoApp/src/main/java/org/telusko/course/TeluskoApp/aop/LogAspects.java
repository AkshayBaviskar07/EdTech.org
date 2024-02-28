package org.telusko.course.TeluskoApp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspects {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspects.class);

    @AfterThrowing("execution(* org.telusko.course.TeluskoApp.service..*(..))")
    public void afterThrowingExceptionInService(JoinPoint point){
        LOGGER.error("Exception thrown in " + point.getSignature().getName() + " method");
    }

    @AfterThrowing("execution(* org.telusko.course.TeluskoApp.controller..*(..))")
    public void afterThrowingExceptionInController(JoinPoint point){
        LOGGER.error("Exception thrown in " + point.getSignature().getName() + " method");
    }
}
