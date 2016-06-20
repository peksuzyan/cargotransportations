package com.tsystems.cargotransportations.presentation.servlets;

import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.service.abstracts.TruckService;
import com.tsystems.cargotransportations.service.implementations.TruckServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.ActionConstants.*;
import static com.tsystems.cargotransportations.constant.MessageConstants.*;
import static com.tsystems.cargotransportations.constant.PageConstants.TRUCKS_LIST_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.CONFIRMATION_ADMIN_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.TRUCK_REGISTRATION_PAGE;
import static com.tsystems.cargotransportations.constant.ParamConstants.*;

/**
 * Processes all client requests that relate to truck entity.
 */
public class TruckServlet extends EntityServlet<Truck> {
    /**
     * Implementation instance of TruckService class.
     */
    private TruckService truckService = new TruckServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = getActionParam(request);
        switch (action) {
            case REFRESH_ACTION: {
                processRefresh(request, response, TRUCKS_LIST_PARAM, TRUCKS_LIST_PAGE, truckService.getAllTrucks());
            }
            break;
            case ADD_ACTION: {
                processAdd(request, response, TRUCK_REGISTRATION_PAGE);
            }
            break;
            case EDIT_ACTION: {
                String truckNumberParam = request.getParameter(TRUCK_NUMBER_PARAM);
                request.setAttribute(TRUCK_PARAM, truckService.getByNumber(truckNumberParam));
                request.setAttribute(ACTION_PARAM, EDIT_ACTION);
                getServletContext().getRequestDispatcher(TRUCK_REGISTRATION_PAGE).forward(request, response);
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
        String action = getActionParam(request);
        switch (action) {
            case PERFORM_ADDING_ACTION: {
                try {
                    String truckNumberParam = request.getParameter(TRUCK_NUMBER_PARAM);
                    String peopleParam = request.getParameter(PEOPLE_PARAM);
                    String activeParam = request.getParameter(ACTIVE_PARAM);
                    String capacityParam = request.getParameter(CAPACITY_PARAM);
                    String cityParam = request.getParameter(CITY_PARAM);
                    int people = Integer.parseInt(peopleParam);
                    boolean active = Boolean.parseBoolean(activeParam);
                    double capacity = Double.parseDouble(capacityParam);
                    truckService.createTruck(truckNumberParam, people, active, capacity, cityParam);
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, TRUCK_IS_CREATED);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + TRUCK_REGISTRATION_PAGE);
                }
            }
            break;
            case PERFORM_EDITING_ACTION: {
                try {
                    String truckNumberParam = request.getParameter(TRUCK_NUMBER_PARAM);
                    String peopleParam = request.getParameter(PEOPLE_PARAM);
                    String activeParam = request.getParameter(ACTIVE_PARAM);
                    String capacityParam = request.getParameter(CAPACITY_PARAM);
                    int people = Integer.parseInt(peopleParam);
                    boolean active = Boolean.parseBoolean(activeParam);
                    double capacity = Double.parseDouble(capacityParam);
                    truckService.changeByNumber(truckNumberParam, people, active, capacity);
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, TRUCK_IS_EDITED);
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + TRUCK_REGISTRATION_PAGE);
                }
            }
            break;
            case PERFORM_DELETING_ACTION: {
                String truckNumberParam = request.getParameter(TRUCK_NUMBER_PARAM);
                truckService.deleteByNumber(truckNumberParam);
                request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, TRUCK_IS_DELETED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
            }
            break;
            default: {
                processDefault(request, response);
            }
        }
    }
}
