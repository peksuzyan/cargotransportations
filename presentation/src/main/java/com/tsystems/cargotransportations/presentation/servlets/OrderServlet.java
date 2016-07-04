package com.tsystems.cargotransportations.presentation.servlets;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.service.implementation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tsystems.cargotransportations.constants.ActionConstants;
import com.tsystems.cargotransportations.constants.MessageConstants;
import com.tsystems.cargotransportations.constants.PageConstants;
import com.tsystems.cargotransportations.constants.ParamConstants;
import com.tsystems.cargotransportations.service.interfaces.*;

/**
 * Processes all client requests that relate to order entity.
 */
public class OrderServlet extends EntityServlet<Order> {
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

    /**
     * Implementation instance of RouteService class.
     */
    private RouteService routeService = new RouteServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = getActionParam(request);
        switch (actionParam) {
            case ActionConstants.REFRESH_ACTION: {
                processRefresh(request, response, ParamConstants.ORDERS_PARAM, PageConstants.ORDERS_LIST_PAGE, orderService.getAllOrders());
            }
            break;
            case ActionConstants.EDIT_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    Order order = orderService.getByNumber(orderNumber);
                    setOrderDataAsAttributes(request, order);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            default: {
                processDefault(request, response);
            }
            break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionParam = getActionParam(request);
        switch (actionParam) {
            case ActionConstants.PERFORM_ADDING_ACTION: {
                Order order = orderService.createOrder();
                setOrderDataAsAttributes(request, order);
                request.setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_CREATED);
                request.getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
            }
            break;
            case ActionConstants.PERFORM_TRUCK_ASSIGNING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    String truckNumberParam = request.getParameter(ParamConstants.TRUCK_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    orderService.excludeAllDriver(orderNumber);
                    Order order = orderService.assignTruckByNumber(orderNumber, truckNumberParam);
                    setOrderDataAsAttributes(request, order);
                    request.setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_EDITED);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case ActionConstants.PERFORM_TRUCK_REFUSING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    orderService.excludeAllDriver(orderNumber);
                    Order order = orderService.refuseTruck(orderNumber);
                    setOrderDataAsAttributes(request, order);
                    request.setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_EDITED);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case ActionConstants.PERFORM_ROUTE_ASSIGNING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    String routeNumberParam = request.getParameter(ParamConstants.ROUTE_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    int routeNumber = Integer.parseInt(routeNumberParam);
                    orderService.excludeAllDriver(orderNumber);
                    orderService.refuseTruck(orderNumber);
                    Order order = orderService.assignRouteByNumber(orderNumber, routeNumber);
                    setOrderDataAsAttributes(request, order);
                    request.setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_EDITED);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                }
            }
            break;
            case ActionConstants.PERFORM_ROUTE_REFUSING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    orderService.excludeAllDriver(orderNumber);
                    orderService.refuseTruck(orderNumber);
                    Order order = orderService.refuseRoute(orderNumber);
                    setOrderDataAsAttributes(request, order);
                    request.setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_EDITED);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            case ActionConstants.PERFORM_DRIVER_ADDING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    String driverNumberParam = request.getParameter(ParamConstants.DRIVER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    Order order = orderService.addDriverByNumber(orderNumber, driverNumber);
                    setOrderDataAsAttributes(request, order);
                    request.setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_EDITED);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                }
            }
            break;
            case ActionConstants.PERFORM_DRIVER_EXCLUDING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    String driverNumberParam = request.getParameter(ParamConstants.DRIVER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    Order order = orderService.excludeDriverByNumber(orderNumber, driverNumber);
                    setOrderDataAsAttributes(request, order);
                    request.setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_EDITED);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case ActionConstants.PERFORM_CARGO_ADDING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    String cargoNumberParam = request.getParameter(ParamConstants.CARGO_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    orderService.excludeAllDriver(orderNumber);
                    orderService.refuseTruck(orderNumber);
                    orderService.refuseRoute(orderNumber);
                    Order order = orderService.addCargoByNumber(orderNumber, cargoNumber);
                    setOrderDataAsAttributes(request, order);
                    request.setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_EDITED);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case ActionConstants.PERFORM_CARGO_EXCLUDING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    String cargoNumberParam = request.getParameter(ParamConstants.CARGO_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    int cargoNumber = Integer.parseInt(cargoNumberParam);
                    orderService.excludeAllDriver(orderNumber);
                    orderService.refuseTruck(orderNumber);
                    orderService.refuseRoute(orderNumber);
                    Order order = orderService.excludeCargoByNumber(orderNumber, cargoNumber);
                    setOrderDataAsAttributes(request, order);
                    request.setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_EDITED);
                    getServletContext().getRequestDispatcher(PageConstants.ORDER_REGISTRATION_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case ActionConstants.PERFORM_ORDER_PROCESSING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    orderService.sendOrderToPerforming(orderNumber);
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_PERFORMING);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    getServletContext().getRequestDispatcher(PageConstants.ORDERS_LIST_PAGE).forward(request, response);
                }
            }
            break;
            case ActionConstants.PERFORM_DELETING_ACTION: {
                try {
                    String orderNumberParam = request.getParameter(ParamConstants.ORDER_NUMBER_PARAM);
                    int orderNumber = Integer.parseInt(orderNumberParam);
                    orderService.deleteByNumber(orderNumber);
                    request.setAttribute(ParamConstants.ORDERS_PARAM, orderService.getAllOrders());
                    request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.ORDER_IS_DELETED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                } catch (NumberFormatException ex) {
                    request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DATA_ARE_NOT_CORRECT);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
                }
            }
            break;
            default: {
                processDefault(request, response);
            }
        }
    }

    private void setOrderDataAsAttributes(HttpServletRequest request, Order order) {
        /**
         * Sets as attribute the order given by order number (int)
         */
        request.setAttribute(ParamConstants.ORDER_PARAM, order);
        /**
         * Sets as attribute all having cargoes by status PREPARED (it's new cargoes)
         */
        request.setAttribute(ParamConstants.SUITABLE_CARGOES_LIST_PARAM,
                cargoService.getSuitableCargoes());
        /**
         * Sets as attribute all possible routes in according with order.
         */
        request.setAttribute(ParamConstants.SUITABLE_ROUTES_LIST_PARAM,
                routeService.getSuitableRoutesByOrder(order));
        /**
         * Sets as attribute all trucks that:
         * 1. are active;
         * 2. aren't busy;
         * 3. are suitable by capacity.
         */
        request.setAttribute(ParamConstants.SUITABLE_TRUCKS_LIST_PARAM,
                truckService.getSuitableTrucksByOrder(order));
        /**
         * Sets as attribute all drivers that:
         * 1. aren't busy;
         * 2. have the same location with truck;
         * 3. are suitable by working time.
         */
        request.setAttribute(ParamConstants.SUITABLE_DRIVERS_LIST_PARAM,
                driverService.getSuitableDriversByOrder(order));
    }
}
