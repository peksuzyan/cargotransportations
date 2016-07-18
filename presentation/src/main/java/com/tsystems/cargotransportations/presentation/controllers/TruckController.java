package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.presentation.grids.Grid;
import com.tsystems.cargotransportations.presentation.grids.GridUtil;
import com.tsystems.cargotransportations.service.interfaces.TruckService;
import com.tsystems.cargotransportations.presentation.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

import static com.tsystems.cargotransportations.constants.ActionConstants.DELETE_ACTION;
import static com.tsystems.cargotransportations.constants.GridConstants.*;
import static com.tsystems.cargotransportations.constants.GridConstants.GRID_SORT_TO;
import static com.tsystems.cargotransportations.constants.GridConstants.REQUEST_JSON_TYPE;
import static com.tsystems.cargotransportations.constants.MessageConstants.*;
import static com.tsystems.cargotransportations.constants.MessageConstants.CODE_ERROR;
import static com.tsystems.cargotransportations.constants.MessageConstants.CODE_SUCCESS;
import static com.tsystems.cargotransportations.constants.ParamConstants.*;
import static com.tsystems.cargotransportations.constants.ParamConstants.ID_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.MESSAGE_PARAM;
import static com.tsystems.cargotransportations.constants.PresentationConstants.*;

@RequestMapping(TRUCK_DIR)
@Controller
public class TruckController {

    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    private TruckService truckService;

    /**
     * Takes a message with internalization supporting from a request.
     * Automatically has bound with controller through Spring context.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Gets requests to show a partial trucks list.
     * @param page current page number
     * @param records count records on a page
     * @param sortBy sort by any field of entity
     * @param sortTo sort direction
     * @return data container with entities
     */
    @RequestMapping(value = LIST_GRID_DIR, method = RequestMethod.GET, produces = REQUEST_JSON_TYPE)
    @ResponseBody
    public Grid<Truck> listGrid(@RequestParam(value = GRID_CURRENT_PAGE) int page,
                                @RequestParam(value = GRID_RECORDS_ON_PAGE) int records,
                                @RequestParam(value = GRID_SORT_BY) String sortBy,
                                @RequestParam(value = GRID_SORT_TO) String sortTo) {
        return GridUtil.buildGrid(truckService, page, records, sortBy, sortTo);
    }

    /**
     * Gets requests to show a trucks list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        uiModel.addAttribute(TRUCK_PARAM, new Truck());
        return TRUCK_LIST_PATH;
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
        uiModel.addAttribute(TRUCK_PARAM, truckService.read(id));
        return TRUCK_EDIT_PATH;
    }

    /**
     * Gets requests to perform editing a specified entity.
     * @param truck entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(value = ID_DIR, method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute(TRUCK_PARAM) Truck truck,
                       BindingResult bindingResult,
                       Model uiModel,
                       RedirectAttributes redirectAttributes,
                       Locale locale) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute(
                    MESSAGE_PARAM, getMessage(CODE_ERROR, CODE_TRUCK_EDIT_ERROR, locale));
            uiModel.addAttribute(TRUCK_PARAM, truck);
            return TRUCK_EDIT_PATH;
        }
        truckService.update(truck);
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, CODE_TRUCK_EDIT_SUCCESS, locale));
        return TRUCK_REDIRECT_PATH;
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
        Truck truck = truckService.read(id);
        truckService.delete(truck);
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, CODE_TRUCK_DELETE_SUCCESS, locale));
        return TRUCK_REDIRECT_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param truck entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute(TRUCK_PARAM) Truck truck,
                      BindingResult bindingResult,
                      Model uiModel,
                      RedirectAttributes redirectAttributes,
                      Locale locale) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute(
                    MESSAGE_PARAM, getMessage(CODE_ERROR, CODE_TRUCK_ADD_ERROR, locale));
            uiModel.addAttribute(TRUCK_PARAM, truck);
            return TRUCK_LIST_PATH;
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(CODE_SUCCESS, CODE_TRUCK_ADD_SUCCESS, locale));
        truckService.create(truck);
        return TRUCK_REDIRECT_PATH;
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
