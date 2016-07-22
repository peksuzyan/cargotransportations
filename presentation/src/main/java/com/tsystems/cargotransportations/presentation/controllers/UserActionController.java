package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.OrderStatus;
import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.presentation.grids.MessageUtil;
import com.tsystems.cargotransportations.service.interfaces.DriverService;
import com.tsystems.cargotransportations.service.interfaces.OrderService;
import com.tsystems.cargotransportations.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

import static com.tsystems.cargotransportations.constants.MessageConstants.*;
import static com.tsystems.cargotransportations.constants.ParamConstants.MESSAGE_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.USER_PARAM;

@Controller
public class UserActionController {

    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    UserService userService;

    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    DriverService driverService;

    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    OrderService orderService;

    /**
     * Takes a message with internalization supporting from a request.
     * Automatically has bound with controller through Spring context.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Dispatches an admin to personal page.
     * @param uiModel uiModel
     * @return logic name of a personal page
     */
    @RequestMapping(value = "/admin/account", method = RequestMethod.GET)
    public String showAdminAccount(Model uiModel) {
        return "/admin/account";
    }

    /**
     * Dispatches a user to personal page.
     * @param uiModel uiModel
     * @return logic name of a personal page
     */
    @RequestMapping(value = "/user/account", method = RequestMethod.GET)
    public String showUserAccount(Model uiModel) {
        UserDetails authUser = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(authUser.getUsername());
        Driver driver = driverService.getByEmail(authUser.getUsername());
        Order order = (driver != null)
                ? orderService.getByStatusAndTruck(OrderStatus.OPEN, driver.getTruck())
                : null;
        uiModel.addAttribute("user", user);
        uiModel.addAttribute("driver", driver);
        uiModel.addAttribute("order", order);
        return "/user/account";
    }

    @RequestMapping(value = "/user/account", method = RequestMethod.POST)
    public String changePassword(@Valid @ModelAttribute(USER_PARAM) User user,
                                 BindingResult bindingResult,
                                 Model uiModel,
                                 RedirectAttributes redirectAttributes,
                                 Locale locale) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute(
                    MESSAGE_PARAM, MessageUtil.getMessage(
                            CODE_ERROR, CODE_USER_EDIT_ERROR, messageSource, locale));
            uiModel.addAttribute(USER_PARAM, user);
            return "/user/account";
        }
        userService.update(user);
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, MessageUtil.getMessage(
                        CODE_SUCCESS, CODE_USER_EDIT_SUCCESS, messageSource, locale));
        return "redirect:/user/account";
    }

}
