package com.tsystems.cargotransportations.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserActionController {

    @RequestMapping(value = "/user/account", method = RequestMethod.GET)
    public String showUserAccount(Model uiModel) {
        return "/user/account";
    }

    @RequestMapping(value = "/admin/account", method = RequestMethod.GET)
    public String showAdminAccount(Model uiModel) {
        return "/admin/account";
    }

}
