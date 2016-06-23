package com.tsystems.cargotransportations.presentation.servlets;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;
import com.tsystems.cargotransportations.service.interfaces.OrderService;
import com.tsystems.cargotransportations.service.implementation.OrderServiceImpl;
import com.tsystems.cargotransportations.service.interfaces.UserService;
import com.tsystems.cargotransportations.service.implementation.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constants.ActionConstants.*;
import static com.tsystems.cargotransportations.constants.MessageConstants.*;
import static com.tsystems.cargotransportations.constants.PageConstants.*;
import static com.tsystems.cargotransportations.constants.ParamConstants.*;

public class UserServlet extends EntityServlet<User> {
    /**
     * Implementation instance of UserService class.
     */
    private UserService userService = new UserServiceImpl();
    /**
     * Implementation instance of OrderService class.
     */
    private OrderService orderService = new OrderServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = getActionParam(request);
        switch (action) {
            case REFRESH_ACTION: {
                processRefresh(request, response, USERS_LIST_PARAM, USERS_LIST_PAGE, userService.getAllUsers());
            }
            break;
            case ADD_ACTION: {
                processAdd(request, response, USER_REGISTRATION_PAGE);
            }
            break;
            case USER_EDIT_ACTION: {
                String userNameParam = request.getParameter(USER_NAME_PARAM);
                request.setAttribute(USER_PARAM, userService.getByName(userNameParam));
                request.setAttribute(ACTION_PARAM, USER_EDIT_ACTION);
                getServletContext().getRequestDispatcher(USER_ACCOUNT_PAGE).forward(request, response);
            }
            break;
            case EDIT_ACTION: {
                String userNameParam = request.getParameter(USER_NAME_PARAM);
                request.setAttribute(USER_PARAM, userService.getByName(userNameParam));
                request.setAttribute(ACTION_PARAM, EDIT_ACTION);
                getServletContext().getRequestDispatcher(USER_REGISTRATION_PAGE).forward(request, response);
            }
            break;
            case SHOW_ASSIGNMENTS_ACTION: {
                try {
                    String driverNumberParam = request.getParameter(DRIVER_NUMBER_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    Order order = orderService.getPerformingOrderByDriverNumber(driverNumber);
                    request.setAttribute(DRIVERS_LIST_PARAM, order.getDrivers());
                    request.setAttribute(TRUCK_NUMBER_PARAM, order.getTruck().getNumber());
                    request.setAttribute(ORDER_NUMBER_PARAM, order.getNumber());
                    request.setAttribute(SUITABLE_ROUTES_LIST_PARAM, order.getRoute().getCities());
                    getServletContext().getRequestDispatcher(USER_ASSIGNMENTS_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, DRIVER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(CONFIRMATION_USER_PAGE).forward(request, response);
                } catch (NullPointerException ex) {
                    request.setAttribute(ERROR_MESSAGE_PARAM, ORDER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(CONFIRMATION_USER_PAGE).forward(request, response);
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
        String action = getActionParam(request);
        switch (action) {
            case PERFORM_ADDING_ACTION: {
                String userNameParam = request.getParameter(USER_NAME_PARAM);
                String userPasswordParam = request.getParameter(USER_PASSWORD_PARAM);
                UserRole userRole = getUserRole(request.getParameter(USER_ROLE_PARAM));
                userService.createUser(userNameParam, userPasswordParam, userRole);
                request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, USER_IS_CREATED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
            }
            break;
            case PERFORM_EDITING_ACTION: {
                String userNameParam = request.getParameter(USER_NAME_PARAM);
                String userPasswordParam = request.getParameter(USER_PASSWORD_PARAM);
                UserRole userRole = getUserRole(request.getParameter(USER_ROLE_PARAM));
                userService.changeByName(userNameParam, DigestUtils.md5Hex(userPasswordParam), userRole);
                request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, USER_IS_EDITED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
            }
            break;
            case PERFORM_DELETING_ACTION: {
                String userNameParam = request.getParameter(USER_NAME_PARAM);
                userService.deleteByName(userNameParam);
                request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, USER_IS_DELETED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
            }
            break;
            case PERFORM_USER_EDITING_ACTION: {
                String userNameParam = request.getParameter(USER_NAME_PARAM);
                String userPasswordParam = request.getParameter(USER_PASSWORD_PARAM);
                userService.changePasswordByName(userNameParam, DigestUtils.md5Hex(userPasswordParam));
                request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM, PASSWORD_IS_EDITED);
                response.sendRedirect(request.getContextPath() + CONFIRMATION_USER_PAGE);
            }
            break;
            default: {
                processDefault(request, response);
            }
        }
    }

    private UserRole getUserRole(String userRoleParam) {
        return UserRole.ADMIN.toString().equals(userRoleParam) ? UserRole.ADMIN : UserRole.USER;
    }
}
