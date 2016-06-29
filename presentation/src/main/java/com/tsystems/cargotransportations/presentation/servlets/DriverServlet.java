package com.tsystems.cargotransportations.presentation.servlets;

import com.tsystems.cargotransportations.entity.Driver;
import com.tsystems.cargotransportations.service.interfaces.DriverService;
import com.tsystems.cargotransportations.service.implementation.DriverServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tsystems.cargotransportations.constants.ActionConstants;
import com.tsystems.cargotransportations.constants.MessageConstants;
import com.tsystems.cargotransportations.constants.PageConstants;
import com.tsystems.cargotransportations.constants.ParamConstants;

/**
 * Processes all client requests that relate to driver entity.
 */
public class DriverServlet extends EntityServlet<Driver> {
    /**
     * Implementation instance of DriverService class.
     */
    private DriverService driverService = new DriverServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = getActionParam(request);
        request.getSession();
        switch (actionParam) {
            case ActionConstants.REFRESH_ACTION: {
                processRefresh(request, response, ParamConstants.DRIVERS_LIST_PARAM, PageConstants.DRIVERS_LIST_PAGE, driverService.getAllDrivers());
            }
            break;
            case ActionConstants.ADD_ACTION: {
                processAdd(request, response, PageConstants.DRIVER_REGISTRATION_PAGE);
            }
            break;
            case ActionConstants.EDIT_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(ParamConstants.DRIVER_NUMBER_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    request.setAttribute(ParamConstants.DRIVER_PARAM, driverService.getByNumber(driverNumber));
                    request.setAttribute(ParamConstants.ACTION_PARAM, ActionConstants.EDIT_ACTION);
                    getServletContext().getRequestDispatcher(PageConstants.DRIVER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DRIVER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(PageConstants.DRIVERS_LIST_PAGE).forward(request, response);
                }
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
                String firstNameParam = request.getParameter(ParamConstants.FIRST_NAME_PARAM);
                String lastNameParam = request.getParameter(ParamConstants.LAST_NAME_PARAM);
                String cityParam = request.getParameter(ParamConstants.CITY_PARAM);
                driverService.createDriver(firstNameParam, lastNameParam, cityParam);
                request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.DRIVER_IS_CREATED);
                response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
            }
            break;
            case ActionConstants.PERFORM_EDITING_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(ParamConstants.DRIVER_NUMBER_PARAM);
                    String firstNameParam = request.getParameter(ParamConstants.FIRST_NAME_PARAM);
                    String lastNameParam = request.getParameter(ParamConstants.LAST_NAME_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    driverService.changeByNumber(driverNumber, firstNameParam, lastNameParam);
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.DRIVER_IS_EDITED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DRIVER_IS_NOT_FOUND);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                }
            }
            break;
            case ActionConstants.PERFORM_DELETING_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(ParamConstants.DRIVER_NUMBER_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    driverService.deleteByNumber(driverNumber);
                    request.setAttribute(ParamConstants.DRIVERS_LIST_PARAM, driverService.getAllDrivers());
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.DRIVER_IS_DELETED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DRIVER_IS_NOT_FOUND);
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
