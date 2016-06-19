package com.tsystems.cargotransportations.presentation;

import com.tsystems.cargotransportations.service.TruckService;
import com.tsystems.cargotransportations.service.TruckServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.ActionConstants.*;
import static com.tsystems.cargotransportations.constant.MessageConstants.*;
import static com.tsystems.cargotransportations.constant.PageConstants.TRUCKS_LIST_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.CONFIRMATION_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.TRUCK_REGISTRATION_PAGE;
import static com.tsystems.cargotransportations.constant.ParamConstants.*;

/**
 * Processes all client requests that relate to truck entity.
 */
public class TruckServlet extends EntityServlet {
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
                request.setAttribute(TRUCKS_LIST_PARAM, truckService.getAllTrucks());
                getServletContext().getRequestDispatcher(TRUCKS_LIST_PAGE).forward(request, response);
            }
            break;
            case ADD_ACTION: {
                request.setAttribute(ACTION_PARAM, ADD_ACTION);
                getServletContext().getRequestDispatcher(TRUCK_REGISTRATION_PAGE).forward(request, response);
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
                request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ACTION_IS_NOT_EXISTED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
            }
            break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = request.getParameter(ACTION_PARAM);
        String action = (actionParam == null ? NOTHING_ACTION : actionParam);
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
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
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
                    response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
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
                response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
            }
            break;
            default: {
                request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ACTION_IS_NOT_EXISTED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_PAGE);
            } break;
        }
    }
}
