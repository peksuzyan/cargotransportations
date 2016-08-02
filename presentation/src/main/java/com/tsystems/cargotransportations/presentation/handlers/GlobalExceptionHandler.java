package com.tsystems.cargotransportations.presentation.handlers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.tsystems.cargotransportations.constants.mapping.PresentationMapper.PAGE_404;
import static com.tsystems.cargotransportations.constants.mapping.PresentationMapper.REDIRECT;

/**
 * Intercepts all undefined exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Classic log4j logger.
     */
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    /**
     * Method in order to intercept undefined exceptions and log its.
     * @param ex exception
     * @return logic path to default error page
     */
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex) {
        logger.error(ex.getMessage());
        return REDIRECT + PAGE_404;
    }
}
