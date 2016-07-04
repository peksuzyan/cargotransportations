package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.service.interfaces.OrderService;
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
import static com.tsystems.cargotransportations.constants.ParamConstants.NUMBER_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.ORDERS_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.ORDER_PARAM;
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
     * Gets requests to show a entities list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Order> orders = orderService.getAll();
        uiModel.addAttribute(ORDERS_PARAM, orders);
        return ORDER_LIST_PATH;
    }

    /**
     * Gets requests to show edit form with specified entity by number.
     * @param number entity number
     * @param uiModel UI model
     * @return path to logic page of editing form
     */
    @RequestMapping(value = NUMBER_DIR, params = EDIT_ACTION, method = RequestMethod.GET)
    public String editForm(@PathVariable(NUMBER_PARAM) int number, Model uiModel) {
        Order order = orderService.getByNumber(number);
        uiModel.addAttribute(ORDER_PARAM, order);
        return ORDER_EDIT_PATH;
    }

    /**
     * Gets requests to perform editing a specified entity.
     * @param order entity
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(value = NUMBER_DIR, params = EDIT_ACTION, method = RequestMethod.POST)
    public String edit(@ModelAttribute(ORDER_PARAM) Order order, Model uiModel) {
        uiModel.asMap().clear();
        orderService.update(order);
        return ORDER_REDIRECT_PATH + order.getNumber();
    }

    /**
     * Gets requests to delete specified entity by number.
     * @param number number
     * @param uiModel UI model
     * @return redirect path to logic page of entities list
     */
    @RequestMapping(value = NUMBER_DIR, params = DELETE_ACTION, method = RequestMethod.POST)
    public String delete(@PathVariable(NUMBER_PARAM) int number, Model uiModel) {
        Order order = orderService.getByNumber(number);
        orderService.delete(order);
        uiModel.asMap().clear();
        return ORDER_REDIRECT_PATH;
    }

    /**
     * Gets requests to show create form.
     * @param uiModel UI model
     * @return path to logic page of creating form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        Order order = new Order();
        uiModel.addAttribute(ORDER_PARAM, order);
        return ORDER_ADD_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param order order
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.POST)
    public String add(@ModelAttribute(ORDER_PARAM) Order order, Model uiModel) {
        uiModel.asMap().clear();
        orderService.create(order);
        return ORDER_REDIRECT_PATH + order.getNumber();
    }
}
