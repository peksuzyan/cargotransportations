package com.tsystems.cargotransportations.presentation;

import com.tsystems.cargotransportations.service.DriverService;
import com.tsystems.cargotransportations.service.DriverServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.ActionConstants.*;
import static com.tsystems.cargotransportations.constant.MessageConstants.*;
import static com.tsystems.cargotransportations.constant.PageConstants.DRIVERS_LIST_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.CONFIRMATION_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.DRIVER_REGISTRATION_PAGE;
import static com.tsystems.cargotransportations.constant.ParamConstants.*;

/**
 * Processes all client requests that relate to driver entity.
 */
public class DriverServlet extends EntityServlet {
    /**
     * Implementation instance of DriverService class.
     */
    private DriverService driverService = new DriverServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = getActionParam(request);
        switch (actionParam) {
            case REFRESH_ACTION: {
                request.setAttribute(DRIVERS_LIST_PARAM, driverService.getAllDrivers());
                getServletContext().getRequestDispatcher(DRIVERS_LIST_PAGE).forward(request, response);
            }
            break;
            case ADD_ACTION: {
                request.setAttribute(ACTION_PARAM, ADD_ACTION);
                getServletContext().getRequestDispatcher(DRIVER_REGISTRATION_PAGE).forward(request, response);
            }
            break;
            case EDIT_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(DRIVER_NUMBER_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    request.setAttribute(DRIVER_PARAM, driverService.getByNumber(driverNumber));
                    request.setAttribute(ACTION_PARAM, EDIT_ACTION);
                    getServletContext().getRequestDispatcher(DRIVER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, DRIVER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(DRIVERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            default: {
                request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ACTION_IS_NOT_EXISTED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
            }
            break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = getActionParam(request);
        switch (actionParam) {
            case PERFORM_ADDING_ACTION: {
                String firstNameParam = request.getParameter(FIRST_NAME_PARAM);
                String lastNameParam = request.getParameter(LAST_NAME_PARAM);
                String cityParam = request.getParameter(CITY_PARAM);
                driverService.createDriver(firstNameParam, lastNameParam, cityParam);
                request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, DRIVER_IS_CREATED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
            }
            break;
            case PERFORM_EDITING_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(DRIVER_NUMBER_PARAM);
                    String firstNameParam = request.getParameter(FIRST_NAME_PARAM);
                    String lastNameParam = request.getParameter(LAST_NAME_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    driverService.changeByNumber(driverNumber, firstNameParam, lastNameParam);
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, DRIVER_IS_EDITED);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, DRIVER_IS_NOT_FOUND);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
                }
            }
            break;
            case PERFORM_DELETING_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(DRIVER_NUMBER_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    driverService.deleteByNumber(driverNumber);
                    request.setAttribute(DRIVERS_LIST_PARAM, driverService.getAllDrivers());
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, DRIVER_IS_DELETED);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, DRIVER_IS_NOT_FOUND);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
                }
            }
            break;
            default: {
                request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ACTION_IS_NOT_EXISTED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
            }
            break;
        }
    }
}
