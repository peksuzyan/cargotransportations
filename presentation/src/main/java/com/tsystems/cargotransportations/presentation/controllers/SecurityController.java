package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.presentation.grids.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

import static com.tsystems.cargotransportations.constants.codes.MessageCodes.*;
import static com.tsystems.cargotransportations.constants.mapping.ParamMapper.MESSAGE_PARAM;

@Controller
public class SecurityController {

    /**
     * Takes a message with internalization supporting from a request.
     * Automatically has bound with controller through Spring context.
     */
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/404")
    public String undefined(Model uiModel, Locale locale) {
        return "/404";
    }

    @RequestMapping(value = "/login")
    public String login(Model uiModel, Locale locale) {
        return "/login";
    }

    @RequestMapping(value = "/login", params = "error")
    public String error(Model uiModel, Locale locale) {
        uiModel.addAttribute(
                MESSAGE_PARAM, getMessage(CODE_ERROR, SECURITY_WRONG_CREDENTIALS, locale));
        return "/login";
    }

    @RequestMapping(value = "/login", params = "403")
    public String denied(Model uiModel, Locale locale) {
        uiModel.addAttribute(
                MESSAGE_PARAM, getMessage(CODE_ERROR, SECURITY_PERMISSION_DENIED, locale));
        return "/login";
    }

    @RequestMapping(value = "/login", params = "logout")
    public String logout(Model uiModel, Locale locale) {
        uiModel.addAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, SECURITY_LOGOUT, locale));
        return "/login";
    }

    private Message getMessage(String type, String messageCode, Locale locale) {
        return new Message(type,
                messageSource.getMessage(messageCode, new Object[]{}, locale));
    }
}
