package com.tsystems.cargotransportations.presentation;

import com.tsystems.cargotransportations.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.ActionConstants.*;
import static com.tsystems.cargotransportations.constant.MessageConstants.*;
import static com.tsystems.cargotransportations.constant.PageConstants.ORDERS_LIST_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.ORDER_CONFIRMATION_PAGE;
import static com.tsystems.cargotransportations.constant.PageConstants.ORDER_REGISTRATION_PAGE;
import static com.tsystems.cargotransportations.constant.ParamConstants.*;

/**
 * Processes all client requests that relate to order entity.
 */
public class OrderServlet extends HttpServlet {
    /**
     * Implementation instance of OrderService class.
     */
    private OrderService orderService = new OrderServiceImpl();

    /**
     * Implementation instance of DriverService class.
     */
    private DriverService driverService = new DriverServiceImpl();

    /**
     * Implementation instance of TruckService class.
     */
    private TruckService truckService = new TruckServiceImpl();

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
                request.setAttribute(ORDERS_LIST_PARAM, orderService.getAllOrders());
                getServletContext().getRequestDispatcher(ORDERS_LIST_PAGE).forward(request, response);
            }
            break;
            case EDIT_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ORDER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    setOrderDataAsAttributes(request, orderNumber);
                    //request.setAttribute(ACTION_PARAM, EDIT_ACTION);
                    getServletContext().getRequestDispatcher(ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, ORDER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            default: {
                request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ACTION_IS_NOT_EXISTED);
                response.sendRedirect(request.getContextPath() + ORDER_CONFIRMATION_PAGE);
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
                orderService.createOrder();
                request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, ORDER_IS_CREATED);
                response.sendRedirect(request.getContextPath() + ORDER_CONFIRMATION_PAGE);
            }
            break;
            case PERFORM_TRUCK_ASSIGNING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ORDER_NUMBER_PARAM);
                    String truckNumberParam = request.getParameter(TRUCK_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    orderService.assignTruckByNumber(orderNumber, truckNumberParam);
                    setOrderDataAsAttributes(request, orderNumber);
                    response.sendRedirect(request.getContextPath() + ORDER_REGISTRATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, ORDER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case PERFORM_TRUCK_REFUSING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ORDER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    orderService.refuseTruck(orderNumber);
                    setOrderDataAsAttributes(request, orderNumber);
                    response.sendRedirect(request.getContextPath() + ORDER_REGISTRATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, ORDER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case PERFORM_DRIVER_ADDING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ORDER_NUMBER_PARAM);
                    String driverNumberParam = request.getParameter(DRIVER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    orderService.addDriverByNumber(orderNumber, driverNumber);
                    setOrderDataAsAttributes(request, orderNumber);
                    response.sendRedirect(request.getContextPath() + ORDER_REGISTRATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, ORDER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case PERFORM_CARGO_ADDING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ORDER_NUMBER_PARAM);
                    String cargoNumberParam = request.getParameter(CARGO_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    orderService.addCargoByNumber(orderNumber, cargoNumber);
                    setOrderDataAsAttributes(request, orderNumber);
                    response.sendRedirect(request.getContextPath() + ORDER_REGISTRATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, ORDER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case PERFORM_DRIVER_EXCLUDING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ORDER_NUMBER_PARAM);
                    String driverNumberParam = request.getParameter(DRIVER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    orderService.excludeDriverByNumber(orderNumber, driverNumber);
                    setOrderDataAsAttributes(request, orderNumber);
                    response.sendRedirect(request.getContextPath() + ORDER_REGISTRATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, ORDER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case PERFORM_CARGO_EXCLUDING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ORDER_NUMBER_PARAM);
                    String cargoNumberParam = request.getParameter(CARGO_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    orderService.excludeCargoByNumber(orderNumber, cargoNumber);
                    setOrderDataAsAttributes(request, orderNumber);
                    response.sendRedirect(request.getContextPath() + ORDER_REGISTRATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, ORDER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case PERFORM_ORDER_PROCESSING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ORDER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    orderService.sendOrderToPerforming(orderNumber);
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, ORDER_IS_PERFORMING);
                    response.sendRedirect(request.getContextPath() + ORDER_CONFIRMATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, ORDER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case PERFORM_DELETING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ORDER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    orderService.deleteByNumber(orderNumber);
                    request.setAttribute(ORDERS_LIST_PARAM, orderService.getAllOrders());
                    request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, ORDER_IS_DELETED);
                    response.sendRedirect(request.getContextPath() + ORDER_CONFIRMATION_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ERROR_MESSAGE_PARAM, DRIVER_IS_NOT_FOUND);
                    response.sendRedirect(request.getContextPath() + ORDER_CONFIRMATION_PAGE);
                }
            }
            break;
            default:
                break;
        }
    }

    private String getActionParam(HttpServletRequest request) {
        String actionParam = request.getParameter(ACTION_PARAM);
        return (actionParam == null ? NOTHING_ACTION : actionParam);
    }

    private void setOrderDataAsAttributes(HttpServletRequest request, int orderNumber) {
        request.setAttribute(ORDER_PARAM, orderService.getByNumber(orderNumber));
        request.setAttribute(SUITABLE_TRUCKS_LIST_PARAM,
                truckService.getSuitableTrucksByOrder(orderNumber));
        request.setAttribute(SUITABLE_CARGOES_LIST_PARAM,
                cargoService.getSuitableCargoesByOrder(orderNumber));
        request.setAttribute(SUITABLE_DRIVERS_LIST_PARAM,
                driverService.getSuitableDriversByOrder(orderNumber));
    }
}
