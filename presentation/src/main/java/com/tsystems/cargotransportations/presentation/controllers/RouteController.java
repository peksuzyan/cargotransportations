package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Route;
import com.tsystems.cargotransportations.service.interfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.tsystems.cargotransportations.constants.ActionConstants.DELETE_ACTION;
import static com.tsystems.cargotransportations.constants.ParamConstants.NUMBER_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.ROUTES_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.ROUTE_PARAM;
import static com.tsystems.cargotransportations.constants.PresentationConstants.*;

@RequestMapping(ROUTE_DIR)
@Controller
public class RouteController {
    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    RouteService routeService;

    /**
     * Gets requests to show a entities list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        List<Route> routes = routeService.getAll();
        uiModel.addAttribute(ROUTES_PARAM, routes);
        return ROUTE_LIST_PATH;
    }

    /**
     * Gets requests to delete specified user by name.
     * @param number number
     * @param uiModel UI model
     * @return redirect path to logic page of entities list
     */
    @RequestMapping(value = ROUTE_DIR, params = DELETE_ACTION, method = RequestMethod.POST)
    public String delete(@PathVariable(NUMBER_PARAM) int number, Model uiModel) {
        Route route = routeService.getByNumber(number);
        routeService.delete(route);
        uiModel.asMap().clear();
        return ROUTE_REDIRECT_PATH;
    }

    /**
     * Gets requests to show create form.
     * @param uiModel UI model
     * @return path to logic page of creating form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        Route route = new Route();
        uiModel.addAttribute(ROUTE_PARAM, route);
        return ROUTE_ADD_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param route route
     * @param uiModel UI model
     * @return redirect path to logic page of editing form
     */
    @RequestMapping(params = ADD_DIR, method = RequestMethod.POST)
    public String add(@ModelAttribute(ROUTE_PARAM) Route route, Model uiModel) {
        uiModel.asMap().clear();
        routeService.create(route);
        return ROUTE_REDIRECT_PATH + route.getNumber();
    }
}
