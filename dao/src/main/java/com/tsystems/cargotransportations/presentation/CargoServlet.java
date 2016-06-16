package com.tsystems.cargotransportations.presentation;

import com.tsystems.cargotransportations.service.CargoService;
import com.tsystems.cargotransportations.service.CargoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.ActionConstant.*;
import static com.tsystems.cargotransportations.constant.MessageConstant.*;
import static com.tsystems.cargotransportations.constant.PageConstant.CARGO_CONFIRMATION_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstant.CARGOES_LIST_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstant.CARGO_REGISTRATION_PAGE;
import static com.tsystems.cargotransportations.constant.ParamConstant.*;

/**
 * Processes all client requests that relate to cargo entity.
 */
public class CargoServlet extends HttpServlet {
    /**
     * Implementation instance of CargoService class.
     */
    private CargoService cargoService = new CargoServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = getActionParam(request);
        switch (actionParam) {
            case REFRESH_ACTION: {
                request.setAttribute(CARGOES_LIST_PARAM, cargoService.getAllCargoes());
                getServletContext().getRequestDispatcher(CARGOES_LIST_PAGE).forward(request, response);
            }
            break;
            case ADD_ACTION: {
                request.setAttribute(ACTION_PARAM, ADD_ACTION);
                getServletContext().getRequestDispatcher(CARGO_REGISTRATION_PAGE).forward(request, response);
            }
            break;
            case EDIT_ACTION: {
                try {
                    String cargoNumberParam = request.getParameter(CARGO_NUMBER_PARAM);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    request.setAttribute(CARGO_PARAM, cargoService.getByNumber(cargoNumber));
                    request.setAttribute(ACTION_PARAM, EDIT_ACTION);
                    getServletContext().getRequestDispatcher(CARGO_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, CARGO_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(CARGOES_LIST_PAGE).forward(request, response);
                }
            }
            break;
            default: {
                request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ACTION_IS_NOT_EXISTED);
                response.sendRedirect(request.getContextPath() + CARGO_CONFIRMATION_PAGE);
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
                try {
                    String nameParam = request.getParameter(NAME_PARAM);
                    String weightParam = request.getParameter(WEIGHT_PARAM);
                    String departureCityParam = request.getParameter(DEPARTURE_CITY_PARAM);
                    String arrivalCityParam = request.getParameter(ARRIVAL_CITY_PARAM);
                    double weight = Double.parseDouble(weightParam);
                    cargoService.createCargo(nameParam, weight, departureCityParam, arrivalCityParam);
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, CARGO_IS_CREATED);
                    response.sendRedirect(request.getContextPath() + CARGO_CONFIRMATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + CARGO_REGISTRATION_PAGE);
                }
            }
            break;
            case PERFORM_EDITING_ACTION: {
                try {
                    String cargoNumberParam = request.getParameter(CARGO_NUMBER_PARAM);
                    String nameParam = request.getParameter(NAME_PARAM);
                    String weightParam = request.getParameter(WEIGHT_PARAM);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    double weight = Double.parseDouble(weightParam);
                    cargoService.changeByNumber(cargoNumber, nameParam, weight);
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, CARGO_IS_EDITED);
                    response.sendRedirect(request.getContextPath() + CARGO_CONFIRMATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + CARGO_REGISTRATION_PAGE);
                }
            }
            break;
            case PERFORM_DELETING_ACTION: {
                try {
                    String cargoNumberParam = request.getParameter(CARGO_NUMBER_PARAM);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    cargoService.deleteByNumber(cargoNumber);
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, CARGO_IS_DELETED);
                    response.sendRedirect(request.getContextPath() + CARGO_CONFIRMATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + CARGO_REGISTRATION_PAGE);
                }
            }
            break;
            default: {
                request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ACTION_IS_NOT_EXISTED);
                response.sendRedirect(request.getContextPath() + CARGO_CONFIRMATION_PAGE);
            }
            break;
        }
    }

    private String getActionParam(HttpServletRequest request) {
        String actionParam = request.getParameter(ACTION_PARAM);
        return (actionParam == null ? NOTHING_ACTION : actionParam);
    }
}
