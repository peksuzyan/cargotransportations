package com.tsystems.cargotransportations.presentation.handlers;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.tsystems.cargotransportations.constants.PresentationConstants.PAGE_404;
import static com.tsystems.cargotransportations.constants.PresentationConstants.REDIRECT;

//@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    //@ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex) {
        logger.error(ex.getMessage());
        return REDIRECT + PAGE_404;
    }
}
