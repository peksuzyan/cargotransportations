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

import static com.tsystems.cargotransportations.constants.ActionConstants.*;
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
     * Gets requests to creating empty order.
     *
     * @return id of a created order
     */
    @RequestMapping(
            value = ORDER_NEW_PATH,
            method = RequestMethod.GET,
            produces = REQUEST_JSON_TYPE)
    @ResponseBody
    public int createEmptyOrder() {
        Order order = new Order();
        orderService.create(order);
        return order.getId();
    }

    /**
     * Gets requests to verifying an order in according with business-logic requirements.
     *
     * @param id     order id
     * @param locale locale
     * @return message object
     */
    @RequestMapping(
            value = ID_DIR,
            method = RequestMethod.GET,
            params = VERIFY_ACTION,
            produces = REQUEST_JSON_TYPE,
            consumes = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message verify(@PathVariable(ID_PARAM) int id,
                          Locale locale) {
        try {
            orderService.isReadyToPerforming(orderService.read(id));
        } catch (ServiceException e) {
            return MessageUtil.getMessage(CODE_FAILURE, e.getMessage(), messageSource, locale);
        }
        return new Message(CODE_PASSED, null);
    }

    /**
     * Gets requests to creating filled order.
     *
     * @return logic path to a redirect page
     */
    @RequestMapping(
            value = ORDER_NEW_PATH,
            method = RequestMethod.POST,
            produces = REQUEST_JSON_TYPE)
    public String createFilledOrder(Model uiModel,
                                    RedirectAttributes redirectAttributes,
                                    Locale locale) {
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, MessageUtil.getMessage(
                        CODE_SUCCESS, CODE_ORDER_ADD_SUCCESS, messageSource, locale));
        return ORDER_REDIRECT_PATH;
    }

    /**
     * Gets requests to adding cargo to an order.
     *
     * @param orderId orderId
     * @param cargoId cargoId
     * @param locale  locale
     * @return is added or not
     */
    @RequestMapping(
            value = ORDER_CARGO_PATH,
            method = RequestMethod.POST,
            params = ADD_ACTION,
            produces = REQUEST_JSON_TYPE,
            consumes = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message addCargo(@RequestParam(value = ORDER_PARAM) int orderId,
                            @RequestParam(value = CARGO_PARAM) int cargoId,
                            Locale locale) {
        return orderService.addCargoById(orderId, cargoId)
                ? MessageUtil.getMessage(CODE_PASSED, CODE_OPERATION_SUCCESS, messageSource, locale)
                : MessageUtil.getMessage(CODE_FAILURE, CODE_OPERATION_ERROR, messageSource, locale);
    }

    /**
     * Gets requests to clearing cargoes list of an order.
     *
     * @param orderId orderId
     * @param locale  locale
     * @return is cleared or not
     */
    @RequestMapping(
            value = ORDER_CARGO_PATH,
            method = RequestMethod.POST,
            params = CLEAR_ACTION,
            produces = REQUEST_JSON_TYPE,
            consumes = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message clearCargoes(@RequestParam(value = ORDER_PARAM) int orderId,
                                Locale locale) {
        return orderService.clearCargoes(orderId)
                ? MessageUtil.getMessage(CODE_PASSED, CODE_OPERATION_SUCCESS, messageSource, locale)
                : MessageUtil.getMessage(CODE_FAILURE, CODE_OPERATION_ERROR, messageSource, locale);
    }

    /**
     * Gets requests to assigning truck to an order.
     *
     * @param orderId orderId
     * @param truckId truckId
     * @param locale  locale
     * @return is added or not
     */
    @RequestMapping(
            value = ORDER_TRUCK_PATH,
            method = RequestMethod.POST,
            params = ADD_ACTION,
            produces = REQUEST_JSON_TYPE,
            consumes = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message assignTruckById(@RequestParam(value = ORDER_PARAM) int orderId,
                                   @RequestParam(value = TRUCK_PARAM) int truckId,
                                   Locale locale) {
        return orderService.assignCargoById(orderId, truckId)
                ? MessageUtil.getMessage(CODE_PASSED, CODE_OPERATION_SUCCESS, messageSource, locale)
                : MessageUtil.getMessage(CODE_FAILURE, CODE_OPERATION_ERROR, messageSource, locale);
    }

    /**
     * Gets requests to rejecting truck of an order.
     *
     * @param orderId orderId
     * @param locale  locale
     * @return is rejected or not
     */
    @RequestMapping(
            value = ORDER_TRUCK_PATH,
            method = RequestMethod.POST,
            params = CLEAR_ACTION,
            produces = REQUEST_JSON_TYPE,
            consumes = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message rejectTruck(@RequestParam(value = ORDER_PARAM) int orderId,
                               Locale locale) {
        return orderService.rejectTruck(orderId)
                ? MessageUtil.getMessage(CODE_PASSED, CODE_OPERATION_SUCCESS, messageSource, locale)
                : MessageUtil.getMessage(CODE_FAILURE, CODE_OPERATION_ERROR, messageSource, locale);
    }

    /**
     * Gets requests to adding driver to an order.
     *
     * @param orderId  orderId
     * @param driverId driverId
     * @param locale   locale
     * @return is added or not
     */
    @RequestMapping(
            value = ORDER_DRIVER_PATH,
            method = RequestMethod.POST,
            params = ADD_ACTION,
            produces = REQUEST_JSON_TYPE,
            consumes = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message addDriver(@RequestParam(value = ORDER_PARAM) int orderId,
                             @RequestParam(value = DRIVER_PARAM) int driverId,
                             Locale locale) {
        return orderService.addDriverById(orderId, driverId)
                ? MessageUtil.getMessage(CODE_PASSED, CODE_OPERATION_SUCCESS, messageSource, locale)
                : MessageUtil.getMessage(CODE_FAILURE, CODE_OPERATION_ERROR, messageSource, locale);
    }

    /**
     * Gets requests to clearing drivers list of an order.
     *
     * @param orderId orderId
     * @param locale  locale
     * @return is cleared or not
     */
    @RequestMapping(
            value = ORDER_DRIVER_PATH,
            method = RequestMethod.POST,
            params = CLEAR_ACTION,
            produces = REQUEST_JSON_TYPE,
            consumes = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message clearDrivers(@RequestParam(value = ORDER_PARAM) int orderId,
                                Locale locale) {
        return orderService.clearDrivers(orderId)
                ? MessageUtil.getMessage(CODE_PASSED, CODE_OPERATION_SUCCESS, messageSource, locale)
                : MessageUtil.getMessage(CODE_FAILURE, CODE_OPERATION_ERROR, messageSource, locale);
    }

    /**
     * Gets requests to assigning route to an order.
     *
     * @param orderId orderId
     * @param routeId routeId
     * @param locale  locale
     * @return is added or not
     */
    @RequestMapping(
            value = ORDER_ROUTE_PATH,
            method = RequestMethod.POST,
            params = ADD_ACTION,
            produces = REQUEST_JSON_TYPE,
            consumes = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message assignRouteById(@RequestParam(value = ORDER_PARAM) int orderId,
                                   @RequestParam(value = ROUTE_PARAM) int routeId,
                                   Locale locale) {
        return orderService.assignRoute(orderId, routeId)
                ? MessageUtil.getMessage(CODE_PASSED, CODE_OPERATION_SUCCESS, messageSource, locale)
                : MessageUtil.getMessage(CODE_FAILURE, CODE_OPERATION_ERROR, messageSource, locale);
    }

    /**
     * Gets requests to rejecting route of an order.
     *
     * @param orderId orderId
     * @param locale  locale
     * @return is rejected or not
     */
    @RequestMapping(
            value = ORDER_ROUTE_PATH,
            method = RequestMethod.POST,
            params = CLEAR_ACTION,
            produces = REQUEST_JSON_TYPE,
            consumes = REQUEST_JSON_TYPE)
    @ResponseBody
    public Message rejectRoute(@RequestParam(value = ORDER_PARAM) int orderId,
                               Locale locale) {
        return orderService.rejectRoute(orderId)
                ? MessageUtil.getMessage(CODE_PASSED, CODE_OPERATION_SUCCESS, messageSource, locale)
                : MessageUtil.getMessage(CODE_FAILURE, CODE_OPERATION_ERROR, messageSource, locale);
    }

    /**
     * Gets requests to show a partial orders list.
     *
     * @param page    current page number
     * @param records count records on a page
     * @param sortBy  sort by any field of entity
     * @param sortTo  sort direction
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
     *
     * @param id     order id
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
     *
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
     *
     * @param id      entity id
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
     *
     * @param id      id
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
     *
     * @param order   entity
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
