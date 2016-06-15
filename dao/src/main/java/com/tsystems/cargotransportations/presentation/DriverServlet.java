package com.tsystems.cargotransportations.presentation;

import com.tsystems.cargotransportations.service.DriverService;
import com.tsystems.cargotransportations.service.DriverServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.ActionConstant.*;
import static com.tsystems.cargotransportations.constant.PageConstant.DRIVERS_LIST_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstant.DRIVER_CONFIRMATION_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstant.DRIVER_REGISTRATION_PAGE;
import static com.tsystems.cargotransportations.constant.ParamConstant.*;

/**
 * Processes all client requests that relate to driver entity.
 */
public class DriverServlet extends HttpServlet {
    private DriverService driverService = new DriverServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = request.getParameter(ACTION_PARAM);
        String action = (actionParam == null ? REFRESH_ACTION : actionParam);
        switch (action) {
            case REFRESH_ACTION: {
                request.setAttribute(DRIVERS_LIST_PARAM, driverService.getAllDrivers());
                getServletContext().getRequestDispatcher(DRIVERS_LIST_PAGE).forward(request, response);
            } break;
            case ADD_ACTION: {
                request.setAttribute(ACTION_PARAM, ADD_ACTION);
                getServletContext().getRequestDispatcher(DRIVER_REGISTRATION_PAGE).forward(request,response);
            } break;
            case PERFORM_ADDING_ACTION: {
                String firstNameParam = request.getParameter(FIRST_NAME_PARAM);
                String lastNameParam = request.getParameter(LAST_NAME_PARAM);
                String cityParam = request.getParameter(CITY_PARAM);
                driverService.createDriver(firstNameParam, lastNameParam, cityParam);
                getServletContext().getRequestDispatcher(DRIVER_CONFIRMATION_PAGE).forward(request,response);
            } break;
            case EDIT_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(DRIVER_NUMBER_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    request.setAttribute(DRIVER_PARAM, driverService.getByNumber(driverNumber));
                    request.setAttribute(ACTION_PARAM, EDIT_ACTION);
                    getServletContext().getRequestDispatcher(DRIVER_REGISTRATION_PAGE).forward(request,response);
                } catch (NumberFormatException ex) {
                    String errorMessage = "Driver with this number doesn't exist!";
                    request.setAttribute("error_message", errorMessage);
                    getServletContext().getRequestDispatcher(DRIVERS_LIST_PAGE).forward(request,response);
                    // It's need to set on DRIVERS_LIST_PAGE a form to out errorMessage
                }
            } break;
            case PERFORM_EDITING_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(DRIVER_NUMBER_PARAM);
                    String firstNameParam = request.getParameter(FIRST_NAME_PARAM);
                    String lastNameParam = request.getParameter(LAST_NAME_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    driverService.changeByNumber(driverNumber, firstNameParam, lastNameParam);
                    getServletContext().getRequestDispatcher(DRIVER_CONFIRMATION_PAGE).forward(request,response);
                } catch (NumberFormatException ex) {
                    String errorMessage = "Driver with this number doesn't exist!";
                    request.setAttribute("error_message", errorMessage);
                    getServletContext().getRequestDispatcher(DRIVER_REGISTRATION_PAGE).forward(request,response);
                    // It's need to set on DRIVER_REGISTRATION_PAGE a form to out errorMessage
                }
            } break;
            case DELETE_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(DRIVER_NUMBER_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    driverService.deleteByNumber(driverNumber);
                    String successMessage = "This driver is deleted successfully!";
                    request.setAttribute("successMessage", successMessage);
                    request.setAttribute(DRIVERS_LIST_PARAM, driverService.getAllDrivers());
                    getServletContext().getRequestDispatcher(DRIVERS_LIST_PAGE).forward(request,response);
                } catch (NumberFormatException ex) {
                    String errorMessage = "Driver with this number doesn't exist!";
                    request.setAttribute("errorMessage", errorMessage);
                    getServletContext().getRequestDispatcher(DRIVERS_LIST_PAGE).forward(request,response);
                    // It's need to set on DRIVERS_LIST_PAGE a form to out errorMessage
                }
            } break;
            default: {

            } break;
        }
    }
}
