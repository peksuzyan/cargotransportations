package com.tsystems.cargotransportations.aop;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceActionsLogger {

    private static final Logger logger = Logger.getLogger(ServiceActionsLogger.class);

    @Before("execution(* com.tsystems.cargotransportations.service.interfaces.CargoService.*(..))")
    public void logBeforeGetMethods() {
        logger.log(Level.ERROR, "[BEFORE] Anything getMethod is starting...");
    }

    @After("execution(* com.tsystems.cargotransportations.service.interfaces.CargoService.*(..))")
    public void logAfterGetMethods() {
        logger.log(Level.ERROR, "[AFTER] Anything getMethod is ending...");
    }
}
