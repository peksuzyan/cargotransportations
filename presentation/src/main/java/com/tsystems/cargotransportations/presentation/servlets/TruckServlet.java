package com.tsystems.cargotransportations.presentation.servlets;

import com.tsystems.cargotransportations.entity.Truck;
import com.tsystems.cargotransportations.service.interfaces.TruckService;
import com.tsystems.cargotransportations.service.implementation.TruckServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tsystems.cargotransportations.constants.ActionConstants;
import com.tsystems.cargotransportations.constants.MessageConstants;
import com.tsystems.cargotransportations.constants.PageConstants;
import com.tsystems.cargotransportations.constants.ParamConstants;

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
            case ActionConstants.REFRESH_ACTION: {
                processRefresh(request, response, ParamConstants.TRUCKS_PARAM, PageConstants.TRUCKS_LIST_PAGE, truckService.getAllTrucks());
            }
            break;
            case ActionConstants.ADD_ACTION: {
                processAdd(request, response, PageConstants.TRUCK_REGISTRATION_PAGE);
            }
            break;
            case ActionConstants.EDIT_ACTION: {
                String truckNumberParam = request.getParameter(ParamConstants.TRUCK_NUMBER_PARAM);
                request.setAttribute(ParamConstants.TRUCK_PARAM, truckService.getByNumber(truckNumberParam));
                request.setAttribute(ParamConstants.ACTION_PARAM, ActionConstants.EDIT_ACTION);
                getServletContext().getRequestDispatcher(PageConstants.TRUCK_REGISTRATION_PAGE).forward(request, response);
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
            case ActionConstants.PERFORM_ADDING_ACTION: {
                try {
                    String truckNumberParam = request.getParameter(ParamConstants.TRUCK_NUMBER_PARAM);
                    String peopleParam = request.getParameter(ParamConstants.PEOPLE_PARAM);
                    String activeParam = request.getParameter(ParamConstants.ACTIVE_PARAM);
                    String capacityParam = request.getParameter(ParamConstants.CAPACITY_PARAM);
                    String cityParam = request.getParameter(ParamConstants.CITY_PARAM);
                    int people = Integer.parseInt(peopleParam);
                    boolean active = Boolean.parseBoolean(activeParam);
                    double capacity = Double.parseDouble(capacityParam);
                    truckService.createTruck(truckNumberParam, people, active, capacity, cityParam);
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.TRUCK_IS_CREATED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + PageConstants.TRUCK_REGISTRATION_PAGE);
                }
            }
            break;
            case ActionConstants.PERFORM_EDITING_ACTION: {
                try {
                    String truckNumberParam = request.getParameter(ParamConstants.TRUCK_NUMBER_PARAM);
                    String peopleParam = request.getParameter(ParamConstants.PEOPLE_PARAM);
                    String activeParam = request.getParameter(ParamConstants.ACTIVE_PARAM);
                    String capacityParam = request.getParameter(ParamConstants.CAPACITY_PARAM);
                    int people = Integer.parseInt(peopleParam);
                    boolean active = Boolean.parseBoolean(activeParam);
                    double capacity = Double.parseDouble(capacityParam);
                    truckService.changeByNumber(truckNumberParam, people, active, capacity);
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.TRUCK_IS_EDITED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + PageConstants.TRUCK_REGISTRATION_PAGE);
                }
            }
            break;
            case ActionConstants.PERFORM_DELETING_ACTION: {
                String truckNumberParam = request.getParameter(ParamConstants.TRUCK_NUMBER_PARAM);
                truckService.deleteByNumber(truckNumberParam);
                request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.TRUCK_IS_DELETED);
                response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
            }
            break;
            default: {
                processDefault(request, response);
            }
        }
    }
}
