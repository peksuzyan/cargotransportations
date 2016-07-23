package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.exception.ServiceException;
import com.tsystems.cargotransportations.presentation.grids.Grid;
import com.tsystems.cargotransportations.presentation.grids.GridUtil;
import com.tsystems.cargotransportations.presentation.grids.Message;
import com.tsystems.cargotransportations.presentation.grids.MessageUtil;
import com.tsystems.cargotransportations.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static com.tsystems.cargotransportations.constants.ActionConstants.CHECK_ACTION;
import static com.tsystems.cargotransportations.constants.ActionConstants.DELETE_ACTION;
import static com.tsystems.cargotransportations.constants.ActionConstants.EDIT_ACTION;
import static com.tsystems.cargotransportations.constants.GridConstants.*;
import static com.tsystems.cargotransportations.constants.GridConstants.GRID_SORT_TO;
import static com.tsystems.cargotransportations.constants.GridConstants.REQUEST_JSON_TYPE;
import static com.tsystems.cargotransportations.constants.MessageConstants.*;
import static com.tsystems.cargotransportations.constants.ParamConstants.*;
import static com.tsystems.cargotransportations.constants.ParamConstants.MESSAGE_PARAM;
import static com.tsystems.cargotransportations.constants.PresentationConstants.*;

@RequestMapping(ORDER_DIR)
@Controller
public class OrderController {

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
     * Gets requests to show a partial orders list.
     * @param page current page number
     * @param records count records on a page
     * @param sortBy sort by any field of entity
     * @param sortTo sort direction
     * @return data container with entities
     */
    @RequestMapping(value = LIST_GRID_DIR, method = RequestMethod.GET, produces = REQUEST_JSON_TYPE)
    @ResponseBody
    public Grid<Order> listGrid(@RequestParam(value = GRID_CURRENT_PAGE) int page,
                                @RequestParam(value = GRID_RECORDS_ON_PAGE) int records,
                                @RequestParam(value = GRID_SORT_BY) String sortBy,
                                @RequestParam(value = GRID_SORT_TO) String sortTo) {
        return GridUtil.buildGrid(orderService, page, records, sortBy, sortTo);
    }

    /**
     * Gets requests to check whether order is ready to modifying or not
     * in accordance to a business-logic.
     * @param id order id
     * @param locale client locale
     * @return message object
     */
    @RequestMapping(value = ID_DIR, params = CHECK_ACTION,
            method = RequestMethod.GET, produces = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message check(@PathVariable(ID_PARAM) int id,
                         Locale locale) {
        try {
            orderService.isReadyToModifying(orderService.read(id));
        } catch (ServiceException e) {
            return MessageUtil.getMessage(CODE_FAILURE, e.getMessage(), messageSource, locale);
        }
        return new Message(CODE_PASSED, null);
    }

    /**
     * Gets requests to show a orders list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        uiModel.addAttribute(ORDER_PARAM, new Order());
        return ORDER_LIST_PATH;
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
        uiModel.addAttribute(ORDER_PARAM, orderService.read(id));
        return ORDER_EDIT_PATH;
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
        Order order = orderService.read(id);
        try {
            orderService.checkAndDelete(order);
        } catch (ServiceException e) {
            redirectAttributes.addFlashAttribute(
                    MESSAGE_PARAM, MessageUtil.getMessage(
                            CODE_ERROR, e.getMessage(), messageSource, locale));
            return ORDER_REDIRECT_PATH_WITH + id;
        }
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, MessageUtil.getMessage(
                        CODE_SUCCESS, CODE_ORDER_DELETE_SUCCESS, messageSource, locale));
        return ORDER_REDIRECT_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param order entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(@ModelAttribute(ORDER_PARAM) Order order,
                      Model uiModel,
                      RedirectAttributes redirectAttributes,
                      Locale locale) {
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, MessageUtil.getMessage(
                        CODE_SUCCESS, CODE_ORDER_ADD_SUCCESS, messageSource, locale));
        orderService.create(order);
        return ORDER_REDIRECT_PATH;
    }


}
