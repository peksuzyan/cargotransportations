package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.exception.ServiceException;
import com.tsystems.cargotransportations.presentation.grids.Grid;
import com.tsystems.cargotransportations.presentation.grids.GridUtil;
import com.tsystems.cargotransportations.service.interfaces.CargoService;
import com.tsystems.cargotransportations.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

import static com.tsystems.cargotransportations.constants.ActionConstants.CHECK_ACTION;
import static com.tsystems.cargotransportations.constants.ActionConstants.DELETE_ACTION;
import static com.tsystems.cargotransportations.constants.GridConstants.*;
import static com.tsystems.cargotransportations.constants.MessageConstants.*;
import static com.tsystems.cargotransportations.constants.ParamConstants.*;
import static com.tsystems.cargotransportations.constants.PresentationConstants.*;

@RequestMapping(CARGO_DIR)
@Controller
public class CargoController {

    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    private CargoService cargoService;

    /**
     * Takes a message with internalization supporting from a request.
     * Automatically has bound with controller through Spring context.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Gets requests to show a partial cargoes list.
     * @param page current page number
     * @param records count records on a page
     * @param sortBy sort by any field of entity
     * @param sortTo sort direction
     * @return data container with entities
     */
    @RequestMapping(value = LIST_GRID_DIR, method = RequestMethod.GET, produces = REQUEST_JSON_TYPE)
    @ResponseBody
    public Grid<Cargo> listGrid(@RequestParam(value = GRID_CURRENT_PAGE) int page,
                                @RequestParam(value = GRID_RECORDS_ON_PAGE) int records,
                                @RequestParam(value = GRID_SORT_BY) String sortBy,
                                @RequestParam(value = GRID_SORT_TO) String sortTo) {
        return GridUtil.buildGrid(cargoService, page, records, sortBy, sortTo);
    }

    /**
     * Gets requests to check whether cargo is ready to modifying or not
     * in accordance to a business-logic.
     * @param id cargo id
     * @param locale client locale
     * @return message object
     */
    @RequestMapping(value = ID_DIR, params = CHECK_ACTION,
            method = RequestMethod.GET, produces = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message check(@PathVariable(ID_PARAM) int id,
                         Locale locale) {
        try {
            cargoService.isReadyToModifying(cargoService.read(id));
        } catch (ServiceException e) {
            return getMessage(CODE_FAILURE, e.getMessage(), locale);
        }
        return new Message(CODE_PASSED, null);
    }

    /**
     * Gets requests to show a cargoes list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        uiModel.addAttribute(CARGO_PARAM, new Cargo());
        return CARGO_LIST_PATH;
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
        uiModel.addAttribute(CARGO_PARAM, cargoService.read(id));
        return CARGO_EDIT_PATH;
    }

    /**
     * Gets requests to perform editing a specified entity.
     * @param cargo entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(value = ID_DIR, method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute(CARGO_PARAM) Cargo cargo,
                       BindingResult bindingResult,
                       Model uiModel,
                       RedirectAttributes redirectAttributes,
                       Locale locale) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute(
                    MESSAGE_PARAM, getMessage(CODE_ERROR, CODE_CARGO_EDIT_ERROR, locale));
            uiModel.addAttribute(CARGO_PARAM, cargo);
            return CARGO_EDIT_PATH;
        }
        try {
            cargoService.checkAndUpdate(cargo);
        } catch (ServiceException e) {
            uiModel.addAttribute(
                    MESSAGE_PARAM, getMessage(CODE_ERROR, e.getMessage(), locale));
            return CARGO_EDIT_PATH;
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, CODE_CARGO_EDIT_SUCCESS, locale));
        return CARGO_REDIRECT_PATH;
    }

    /**
     * Gets requests to delete specified entity by id.
     * @param id c
     * @param uiModel UI model
     * @return redirect path to logic page of entities list
     */
    @RequestMapping(value = ID_DIR, params = DELETE_ACTION, method = RequestMethod.POST)
    public String delete(@PathVariable(ID_PARAM) int id,
                         Model uiModel,
                         RedirectAttributes redirectAttributes,
                         Locale locale) {
        uiModel.asMap().clear();
        Cargo cargo = cargoService.read(id);
        try {
            cargoService.checkAndDelete(cargo);
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute(
                    MESSAGE_PARAM, getMessage(CODE_ERROR, e.getMessage(), locale));
            return CARGO_REDIRECT_PATH_WITH + id;
        }
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, CODE_CARGO_DELETE_SUCCESS, locale));
        return CARGO_REDIRECT_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param cargo entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute(CARGO_PARAM) Cargo cargo,
                      BindingResult bindingResult,
                      Model uiModel,
                      RedirectAttributes redirectAttributes,
                      Locale locale) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getCode());
            uiModel.addAttribute(
                    MESSAGE_PARAM, getMessage(CODE_ERROR, CODE_CARGO_ADD_ERROR, locale));
            uiModel.addAttribute(CARGO_PARAM, cargo);
            return CARGO_LIST_PATH;
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, CODE_CARGO_ADD_SUCCESS, locale));
        cargoService.create(cargo);
        return CARGO_REDIRECT_PATH;
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
