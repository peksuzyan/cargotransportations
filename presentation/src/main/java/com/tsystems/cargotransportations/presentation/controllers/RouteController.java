package com.tsystems.cargotransportations.presentation.controllers;

import com.tsystems.cargotransportations.entity.Route;
import com.tsystems.cargotransportations.presentation.grids.Grid;
import com.tsystems.cargotransportations.presentation.grids.GridUtil;
import com.tsystems.cargotransportations.presentation.grids.MessageUtil;
import com.tsystems.cargotransportations.service.interfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Locale;

import static com.tsystems.cargotransportations.constants.mapping.GridMapper.*;
import static com.tsystems.cargotransportations.constants.mapping.GridMapper.GRID_SORT_TO;
import static com.tsystems.cargotransportations.constants.codes.MessageCodes.*;
import static com.tsystems.cargotransportations.constants.mapping.ParamMapper.*;
import static com.tsystems.cargotransportations.constants.mapping.ParamMapper.MESSAGE_PARAM;
import static com.tsystems.cargotransportations.constants.mapping.PresentationMapper.*;

@RequestMapping(ROUTE_DIR)
@Controller
public class RouteController {
    /**
     * Injected instance of service class for entities management.
     */
    @Autowired
    RouteService routeService;

    /**
     * Takes a message with internalization supporting from a request.
     * Automatically has bound with controller through Spring context.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Gets requests to show a partial routes list.
     * @param page current page number
     * @param records count records on a page
     * @param sortBy sort by any field of entity
     * @param sortTo sort direction
     * @return data container with entities
     */
    @RequestMapping(value = LIST_GRID_DIR, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public Grid<Route> listGrid(@RequestParam(value = GRID_CURRENT_PAGE) int page,
                                @RequestParam(value = GRID_RECORDS_ON_PAGE) int records,
                                @RequestParam(value = GRID_SORT_BY) String sortBy,
                                @RequestParam(value = GRID_SORT_TO) String sortTo) {
        return GridUtil.buildGrid(routeService, page, records, sortBy, sortTo);
    }

    /**
     * Gets requests to show a routes list.
     * @param uiModel UI model
     * @return path to logic page of entities list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        uiModel.addAttribute(ROUTE_PARAM, new Route());
        return ROUTE_LIST_PATH;
    }

    /**
     * Gets requests to show add form.
     * @param uiModel UI model
     * @return path to logic page of adding form
     */
    @RequestMapping(value = ID_DIR, method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        uiModel.addAttribute(ROUTE_PARAM, new Route());
        return ROUTE_EDIT_PATH;
    }

    /**
     * Gets requests to perform creating a specified entity.
     * @param route route
     * @param routePoints route points / cities
     * @param uiModel uiModel
     * @param redirectAttributes redirectAttributes
     * @param locale locale
     * @return redirect to routes list
     */
    @RequestMapping(method = RequestMethod.POST)
    public String add(@ModelAttribute(ROUTE_PARAM) Route route,
                      @RequestParam(name = ROUTE_POINTS_PARAM) List<String> routePoints,
                      Model uiModel,
                      RedirectAttributes redirectAttributes,
                      Locale locale) {
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute(
                MESSAGE_PARAM, MessageUtil.getMessage(
                        CODE_SUCCESS, CODE_ROUTE_ADD_SUCCESS, messageSource, locale));
        route.setCities(routePoints);
        routeService.create(route);
        return ROUTE_REDIRECT_PATH;
    }
}
