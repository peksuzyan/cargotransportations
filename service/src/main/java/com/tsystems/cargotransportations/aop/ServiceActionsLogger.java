package com.tsystems.cargotransportations.aop;

import org.apache.log4j.Logger;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Logs all public invoking services.
 */
@Component
@Aspect
public class ServiceActionsLogger {

    /**
     * Classic log4j logger.
     */
    private static final Logger logger = Logger.getLogger(ServiceActionsLogger.class);

    /**
     * The log is before service invoking.
     */
    @Before("execution(public * com.tsystems.cargotransportations.service.interfaces.*.*(..))")
    public void logBeforeInvoking() {
        logger.info("Service method is starting.");
    }

    /**
     * The log is after service invoking.
     */
    @After("execution(public * com.tsystems.cargotransportations.service.interfaces.*.*(..))")
    public void logAfterInvoking() {
        logger.info("Service method is completing.");
    }
}
