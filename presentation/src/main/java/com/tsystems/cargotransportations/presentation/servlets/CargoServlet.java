package com.tsystems.cargotransportations.presentation.servlets;

import com.tsystems.cargotransportations.entity.Cargo;
import com.tsystems.cargotransportations.service.interfaces.CargoService;
import com.tsystems.cargotransportations.service.implementation.CargoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tsystems.cargotransportations.constants.ActionConstants;
import com.tsystems.cargotransportations.constants.MessageConstants;
import com.tsystems.cargotransportations.constants.PageConstants;
import com.tsystems.cargotransportations.constants.ParamConstants;

/**
 * Processes all client requests that relate to cargo entity.
 */
public class CargoServlet extends EntityServlet<Cargo> {
    /**
     * Implementation instance of CargoService class.
     */
    private CargoService cargoService = new CargoServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = getActionParam(request);
        switch (actionParam) {
            case ActionConstants.REFRESH_ACTION: {
                processRefresh(request, response, ParamConstants.CARGOES_PARAM, PageConstants.CARGOES_LIST_PAGE, cargoService.getAllCargoes());
            }
            break;
            case ActionConstants.ADD_ACTION: {
                processAdd(request, response, PageConstants.CARGO_REGISTRATION_PAGE);
            }
            break;
            case ActionConstants.EDIT_ACTION: {
                try {
                    String cargoNumberParam = request.getParameter(ParamConstants.CARGO_NUMBER_PARAM);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    request.setAttribute(ParamConstants.CARGO_PARAM, cargoService.getByNumber(cargoNumber));
                    request.setAttribute(ParamConstants.ACTION_PARAM, ActionConstants.EDIT_ACTION);
                    getServletContext().getRequestDispatcher(PageConstants.CARGO_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.CARGO_IS_NOT_FOUND);
                    response.sendRedirect(request.getContextPath() + PageConstants.CARGOES_LIST_PAGE);
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
                try {
                    String nameParam = request.getParameter(ParamConstants.NAME_PARAM);
                    String weightParam = request.getParameter(ParamConstants.WEIGHT_PARAM);
                    String departureCityParam = request.getParameter(ParamConstants.DEPARTURE_CITY_PARAM);
                    String arrivalCityParam = request.getParameter(ParamConstants.ARRIVAL_CITY_PARAM);
                    double weight = Double.parseDouble(weightParam);
                    cargoService.createCargo(nameParam, weight, departureCityParam, arrivalCityParam);
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.CARGO_IS_CREATED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + PageConstants.CARGOES_LIST_PAGE);
                }
            }
            break;
            case ActionConstants.PERFORM_EDITING_ACTION: {
                try {
                    String cargoNumberParam = request.getParameter(ParamConstants.CARGO_NUMBER_PARAM);
                    String nameParam = request.getParameter(ParamConstants.NAME_PARAM);
                    String weightParam = request.getParameter(ParamConstants.WEIGHT_PARAM);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    double weight = Double.parseDouble(weightParam);
                    cargoService.changeByNumber(cargoNumber, nameParam, weight);
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.CARGO_IS_EDITED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + PageConstants.CARGOES_LIST_PAGE);
                }
            }
            break;
            case ActionConstants.PERFORM_DELETING_ACTION: {
                try {
                    String cargoNumberParam = request.getParameter(ParamConstants.CARGO_NUMBER_PARAM);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    cargoService.deleteByNumber(cargoNumber);
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.CARGO_IS_DELETED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + PageConstants.CARGOES_LIST_PAGE);
                }
            }
            break;
            default: {
                processDefault(request, response);
            }
        }
    }
}
