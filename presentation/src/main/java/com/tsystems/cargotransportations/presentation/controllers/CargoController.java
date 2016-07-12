package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.exception.BOException;
import com.tsystems.cargotransportations.presentation.grids.Grid;
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

import static com.tsystems.cargotransportations.constants.ActionConstants.ADD_ACTION;
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
    public Grid<Cargo> listGrid(@RequestParam(value = GRID_CURRENT_PAGE, required = false) Integer page,
                                @RequestParam(value = GRID_RECORDS_ON_PAGE, required = false) Integer records,
                                @RequestParam(value = GRID_SORT_BY, required = false) String sortBy,
                                @RequestParam(value = GRID_SORT_TO, required = false) String sortTo) {
        Grid<Cargo> cargoGrid = new Grid<>();
        cargoGrid.setData(cargoService.getAllByRange(page, records, sortBy, sortTo));
        cargoGrid.setTotalRecords(cargoService.getTotalCount());
        cargoGrid.setCurrentPage(page);
        int totalPages =
                cargoGrid.getTotalRecords() / records
                        + (cargoGrid.getTotalRecords() % records != 0 ? 1 : 0);
        cargoGrid.setTotalPages(totalPages);
        return cargoGrid;
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
                    MESSAGE_PARAM, getMessage(ERROR_PARAM, CODE_CARGO_EDIT_ERROR, locale));
            uiModel.addAttribute(CARGO_PARAM, cargo);
            return CARGO_EDIT_PATH;
        }
        try {
            cargoService.checkAndUpdate(cargo);
        } catch (BOException e) {
            uiModel.addAttribute(
                    MESSAGE_PARAM, getMessage(ERROR_PARAM, e.getMessage(), locale));
            return CARGO_EDIT_PATH;
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(SUCCESS_PARAM, CODE_CARGO_EDIT_SUCCESS, locale));
        return CARGO_REDIRECT_PATH;
    }

    /**
     * Gets requests to show delete form with specified entity by id.
     * @return path to logic page of deleting form
     */
    @RequestMapping(value = ID_DIR, params = DELETE_ACTION, method = RequestMethod.GET)
    public String deleteForm() {
        return CARGO_DELETE_PATH;
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
        } catch (BOException e) {
            redirectAttributes.addFlashAttribute(
                    MESSAGE_PARAM, getMessage(ERROR_PARAM, e.getMessage(), locale));
            return CARGO_REDIRECT_PATH_WITH + id;
        }
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(SUCCESS_PARAM, CODE_CARGO_DELETE_SUCCESS, locale));
        return CARGO_REDIRECT_PATH;
    }

    /**
     * Gets requests to show create form.
     * @param uiModel UI model
     * @return path to logic page of creating form
     */
    @RequestMapping(params = ADD_ACTION, method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        uiModel.addAttribute(CARGO_PARAM, new Cargo());
        return CARGO_ADD_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param cargo entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(params = ADD_ACTION, method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute(CARGO_PARAM) Cargo cargo,
                      BindingResult bindingResult,
                      Model uiModel,
                      RedirectAttributes redirectAttributes,
                      Locale locale) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getCode());
            uiModel.addAttribute(
                    MESSAGE_PARAM, getMessage(ERROR_PARAM, CODE_CARGO_ADD_ERROR, locale));
            uiModel.addAttribute(CARGO_PARAM, cargo);
            return CARGO_ADD_PATH;
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, getMessage(SUCCESS_PARAM, CODE_CARGO_ADD_SUCCESS, locale));
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
