package com.tsystems.cargotransportations.presentation.servlets;

import com.tsystems.cargotransportations.entity.Route;
import com.tsystems.cargotransportations.service.abstracts.RouteService;
import com.tsystems.cargotransportations.service.implementations.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.ActionConstants.*;
import static com.tsystems.cargotransportations.constant.MessageConstants.*;
import static com.tsystems.cargotransportations.constant.PageConstants.ROUTES_LIST_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.CONFIRMATION_ADMIN_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.ROUTE_REGISTRATION_PAGE;
import static com.tsystems.cargotransportations.constant.ParamConstants.*;

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
            case REFRESH_ACTION: {
                processRefresh(request, response, ROUTES_LIST_PARAM, ROUTES_LIST_PAGE, routeService.getAllRoutes());
            }
            break;
            case ADD_ACTION: {
                processAdd(request, response, ROUTE_REGISTRATION_PAGE);
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
            case PERFORM_ADDING_ACTION: {
                try {
                    String citiesCountParam = request.getParameter(CITIES_COUNT_PARAM);
                    int citiesCount = Integer.parseInt(citiesCountParam);
                    String[] cities = new String[citiesCount];
                    for (int i = 0; i < citiesCount; i++) {
                        cities[i] = request.getParameter(CITY_PARAM + String.valueOf(i + 1));
                    }
                    routeService.createRoute(cities);
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, ROUTE_IS_CREATED);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, CITIES_COUNT_IS_NOT_EXISTED);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
                }
            }
            break;
            case PERFORM_DELETING_ACTION: {
                try {
                    String routeNumberParam = request.getParameter(ROUTE_NUMBER_PARAM);
                    int routeNumber = Integer.parseInt(routeNumberParam);
                    routeService.deleteByNumber(routeNumber);
                    request.setAttribute(ROUTES_LIST_PARAM, routeService.getAllRoutes());
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, ROUTE_IS_DELETED);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ROUTE_IS_NOT_FOUND);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
                }
            }
            break;
            default: {
                processDefault(request, response);
            }
        }
    }
}
