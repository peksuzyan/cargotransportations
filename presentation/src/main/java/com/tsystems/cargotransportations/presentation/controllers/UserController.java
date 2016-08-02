package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.presentation.grids.Grid;
import com.tsystems.cargotransportations.presentation.grids.GridUtil;
import com.tsystems.cargotransportations.service.interfaces.UserService;
import com.tsystems.cargotransportations.presentation.grids.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import java.util.Locale;

import static com.tsystems.cargotransportations.constants.mapping.ActionMapper.DELETE_ACTION;
import static com.tsystems.cargotransportations.constants.mapping.GridMapper.*;
import static com.tsystems.cargotransportations.constants.mapping.GridMapper.GRID_SORT_TO;
import static com.tsystems.cargotransportations.constants.codes.MessageCodes.*;
import static com.tsystems.cargotransportations.constants.codes.MessageCodes.CODE_ERROR;
import static com.tsystems.cargotransportations.constants.codes.MessageCodes.CODE_SUCCESS;
import static com.tsystems.cargotransportations.constants.mapping.ParamMapper.*;
import static com.tsystems.cargotransportations.constants.mapping.PresentationMapper.*;

@RequestMapping(USER_DIR)
@Controller
public class UserController {
    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    UserService userService;

    /**
     * Takes a message with internalization supporting from a request.
     * Automatically has bound with controller through Spring context.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Gets requests to show a partial users list.
     * @param page current page number
     * @param records count records on a page
     * @param sortBy sort by any field of entity
     * @param sortTo sort direction
     * @return data container with entities
     */
    @RequestMapping(value = LIST_GRID_DIR, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public Grid<User> listGrid(@RequestParam(value = GRID_CURRENT_PAGE) int page,
                                @RequestParam(value = GRID_RECORDS_ON_PAGE) int records,
                                @RequestParam(value = GRID_SORT_BY) String sortBy,
                                @RequestParam(value = GRID_SORT_TO) String sortTo) {
        return GridUtil.buildGrid(userService, page, records, sortBy, sortTo);
    }

    /**
     * Gets requests to show a users list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        uiModel.addAttribute(USER_PARAM, new User());
        return USER_LIST_PATH;
    }

    /**
     * Gets requests to show edit form with specified entity by id.
     * @param id entity id
     * @param uiModel UI model
     * @return path to logic page of editing form
     */
    @RequestMapping(value = ID_DIR, method = RequestMethod.GET)
    public String editForm(@PathVariable(ID_PARAM) int id,
                           Model uiModel) {
        uiModel.addAttribute(USER_PARAM, userService.read(id));
        return USER_EDIT_PATH;
    }

    /**
     * Gets requests to perform editing a specified entity.
     * @param user entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(value = ID_DIR, method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute(USER_PARAM) User user,
                       BindingResult bindingResult,
                       Model uiModel,
                       RedirectAttributes redirectAttributes,
                       Locale locale) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute(
                    MESSAGE_PARAM, getMessage(CODE_ERROR, CODE_USER_EDIT_ERROR, locale));
            uiModel.addAttribute(USER_PARAM, user);
            return USER_EDIT_PATH;
        }
        userService.update(user);
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, CODE_USER_EDIT_SUCCESS, locale));
        return USER_REDIRECT_PATH;
    }

    /**
     * Gets requests to delete specified entity by id.
     * @param id id
     * @param uiModel UI model
     * @return redirect path to logic page of entities list
     */
    @RequestMapping(value = ID_DIR, params = DELETE_ACTION, method = RequestMethod.POST)
    public String delete(@PathVariable(ID_PARAM) int id,
                         Model uiModel,
                         RedirectAttributes redirectAttributes,
                         Locale locale) {
        uiModel.asMap().clear();
        User user = userService.read(id);
        userService.delete(user);
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, CODE_USER_DELETE_SUCCESS, locale));
        return CARGO_REDIRECT_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param user entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute(USER_PARAM) User user,
                      BindingResult bindingResult,
                      Model uiModel,
                      RedirectAttributes redirectAttributes,
                      Locale locale) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute(
                    MESSAGE_PARAM, getMessage(CODE_ERROR, CODE_USER_ADD_ERROR, locale));
            uiModel.addAttribute(USER_PARAM, user);
            return USER_LIST_PATH;
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, CODE_USER_ADD_SUCCESS, locale));
        userService.create(user);
        return USER_REDIRECT_PATH;
    }

    /**
     * Converts message type, code and request locale to a Message object.
     * @param type type
     * @param messageCode message code
     * @param locale locale
     * @return message object
     */
    private Message getMessage(String type, String messageCode, Locale locale) {
        return new Message(type,
                messageSource.getMessage(messageCode, new Object[]{}, locale));
    }
}
