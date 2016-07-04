package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.tsystems.cargotransportations.constants.ActionConstants.DELETE_ACTION;
import static com.tsystems.cargotransportations.constants.ActionConstants.EDIT_ACTION;
import static com.tsystems.cargotransportations.constants.ParamConstants.*;
import static com.tsystems.cargotransportations.constants.PresentationConstants.*;

@RequestMapping(USER_DIR)
@Controller
public class UserController {
    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    UserService userService;

    /**
     * Gets requests to show a entities list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<User> users = userService.getAll();
        uiModel.addAttribute(USERS_PARAM, users);
        return USER_LIST_PATH;
    }

    /**
     * Gets requests to show edit form with specified entity by name.
     * @param name user name
     * @param uiModel UI model
     * @return path to logic page of editing form
     */
    @RequestMapping(value = NAME_DIR, params = EDIT_ACTION, method = RequestMethod.GET)
    public String editForm(@PathVariable(NAME_PARAM) String name, Model uiModel) {
        User user = userService.getByName(name);
        uiModel.addAttribute(USER_PARAM, user);
        return USER_EDIT_PATH;
    }

    /**
     * Gets requests to perform editing a specified entity.
     * @param user entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(value = NAME_DIR, params = EDIT_ACTION, method = RequestMethod.POST)
    public String edit(@ModelAttribute(USER_PARAM) User user, Model uiModel) {
        uiModel.asMap().clear();
        userService.update(user);
        return USER_REDIRECT_PATH + user.getName();
    }

    /**
     * Gets requests to delete specified user by name.
     * @param name name
     * @param uiModel UI model
     * @return redirect path to logic page of entities list
     */
    @RequestMapping(value = NAME_DIR, params = DELETE_ACTION, method = RequestMethod.POST)
    public String delete(@PathVariable(NAME_PARAM) String name, Model uiModel) {
        User user = userService.getByName(name);
        userService.delete(user);
        uiModel.asMap().clear();
        return USER_REDIRECT_PATH;
    }

    /**
     * Gets requests to show create form.
     * @param uiModel UI model
     * @return path to logic page of creating form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        User user = new User();
        uiModel.addAttribute(USER_PARAM, user);
        return USER_ADD_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param user user
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.POST)
    public String add(@ModelAttribute(USER_PARAM) User user, Model uiModel) {
        uiModel.asMap().clear();
        userService.create(user);
        return USER_REDIRECT_PATH + user.getName();
    }
}
