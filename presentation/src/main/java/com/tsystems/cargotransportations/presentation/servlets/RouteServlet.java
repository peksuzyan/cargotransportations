package com.tsystems.cargotransportations.presentation.servlets;

import com.tsystems.cargotransportations.entity.Route;
import com.tsystems.cargotransportations.service.interfaces.RouteService;
import com.tsystems.cargotransportations.service.implementation.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tsystems.cargotransportations.constants.ActionConstants;
import com.tsystems.cargotransportations.constants.MessageConstants;
import com.tsystems.cargotransportations.constants.PageConstants;
import com.tsystems.cargotransportations.constants.ParamConstants;

/**
 * Processes all client requests that relate to route entity.
 */
public class RouteServlet extends EntityServlet<Route> {
    /**
     * Implementation instance of RouteService class.
     */
    private RouteService routeService = new RouteServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = getActionParam(request);
        switch (actionParam) {
            case ActionConstants.REFRESH_ACTION: {
                processRefresh(request, response, ParamConstants.ROUTES_LIST_PARAM, PageConstants.ROUTES_LIST_PAGE, routeService.getAllRoutes());
            }
            break;
            case ActionConstants.ADD_ACTION: {
                processAdd(request, response, PageConstants.ROUTE_REGISTRATION_PAGE);
            }
            break;
            default: {
                processDefault(request, response);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = getActionParam(request);
        switch (actionParam) {
            case ActionConstants.PERFORM_ADDING_ACTION: {
                try {
                    String durationParam = request.getParameter(ParamConstants.DURATION_PARAM);
                    String citiesCountParam = request.getParameter(ParamConstants.CITIES_COUNT_PARAM);
                    int duration = Integer.parseInt(durationParam);
                    int citiesCount = Integer.parseInt(citiesCountParam);
                    String[] cities = new String[citiesCount];
                    for (int i = 0; i < citiesCount; i++) {
                        cities[i] = request.getParameter(ParamConstants.CITY_PARAM + String.valueOf(i + 1));
                    }
                    routeService.createRoute(duration, cities);
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ROUTE_IS_CREATED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.CITIES_COUNT_IS_NOT_EXISTED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                }
            }
            break;
            case ActionConstants.PERFORM_DELETING_ACTION: {
                try {
                    String routeNumberParam = request.getParameter(ParamConstants.ROUTE_NUMBER_PARAM);
                    int routeNumber = Integer.parseInt(routeNumberParam);
                    routeService.deleteByNumber(routeNumber);
                    request.setAttribute(ParamConstants.ROUTES_LIST_PARAM, routeService.getAllRoutes());
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ROUTE_IS_DELETED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.ROUTE_IS_NOT_FOUND);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                }
            }
            break;
            default: {
                processDefault(request, response);
            }
        }
    }
}
