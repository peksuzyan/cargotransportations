package com.tsystems.cargotransportations.presentation.servlets;

import com.tsystems.cargotransportations.entity.Order;
import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;
import com.tsystems.cargotransportations.exception.IllegalAccessException;
import com.tsystems.cargotransportations.service.interfaces.OrderService;
import com.tsystems.cargotransportations.service.implementation.OrderServiceImpl;
import com.tsystems.cargotransportations.service.interfaces.UserService;
import com.tsystems.cargotransportations.service.implementation.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tsystems.cargotransportations.constants.ActionConstants;
import com.tsystems.cargotransportations.constants.MessageConstants;
import com.tsystems.cargotransportations.constants.PageConstants;
import com.tsystems.cargotransportations.constants.ParamConstants;

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
            case ActionConstants.REFRESH_ACTION: {
                processRefresh(request, response, ParamConstants.USERS_PARAM, PageConstants.USERS_LIST_PAGE, userService.getAllUsers());
            }
            break;
            case ActionConstants.ADD_ACTION: {
                processAdd(request, response, PageConstants.USER_REGISTRATION_PAGE);
            }
            break;
            case ActionConstants.USER_EDIT_ACTION: {
                String userNameParam = request.getParameter(ParamConstants.USER_NAME_PARAM);
                request.setAttribute(ParamConstants.USER_PARAM, userService.getByName(userNameParam));
                request.setAttribute(ParamConstants.ACTION_PARAM, ActionConstants.USER_EDIT_ACTION);
                getServletContext().getRequestDispatcher(PageConstants.USER_ACCOUNT_PAGE).forward(request, response);
            }
            break;
            case ActionConstants.EDIT_ACTION: {
                String userNameParam = request.getParameter(ParamConstants.USER_NAME_PARAM);
                request.setAttribute(ParamConstants.USER_PARAM, userService.getByName(userNameParam));
                request.setAttribute(ParamConstants.ACTION_PARAM, ActionConstants.EDIT_ACTION);
                getServletContext().getRequestDispatcher(PageConstants.USER_REGISTRATION_PAGE).forward(request, response);
            }
            break;
            case ActionConstants.SHOW_ASSIGNMENTS_ACTION: {
                try {
                    Object userNameParam = request.getSession(false).getAttribute(ParamConstants.USER_NAME_PARAM);
                    User user = userService.getByName((String) userNameParam);
                    String driverNumberParam = request.getParameter(ParamConstants.DRIVER_NUMBER_PARAM);
                    int driverNumber = Integer.parseInt(driverNumberParam);
                    userService.checkUserByDriverNumber(user, driverNumber);
                    Order order = orderService.getPerformingOrderByDriverNumber(driverNumber);
                    request.setAttribute(ParamConstants.DRIVERS_PARAM, order.getDrivers());
                    request.setAttribute(ParamConstants.TRUCK_NUMBER_PARAM, order.getTruck().getNumber());
                    request.setAttribute(ParamConstants.ORDER_NUMBER_PARAM, order.getNumber());
                    request.setAttribute(ParamConstants.SUITABLE_ROUTES_LIST_PARAM, order.getRoute().getCities());
                    getServletContext().getRequestDispatcher(PageConstants.USER_ASSIGNMENTS_PAGE).forward(request, response);
                } catch (NumberFormatException ex) {
                    request.setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.DRIVER_IS_NOT_FOUND);
                    getServletContext().getRequestDispatcher(PageConstants.CONFIRMATION_USER_PAGE).forward(request, response);
                } catch (IllegalAccessException ex) {
                    request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.PERMISSION_DENIED);
                    response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_USER_PAGE);
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
            case ActionConstants.PERFORM_ADDING_ACTION: {
                String userNameParam = request.getParameter(ParamConstants.USER_NAME_PARAM);
                String userPasswordParam = request.getParameter(ParamConstants.USER_PASSWORD_PARAM);
                UserRole userRole = getUserRole(request.getParameter(ParamConstants.USER_ROLE_PARAM));
                String driverNumberParam = request.getParameter(ParamConstants.DRIVER_NUMBER_PARAM);
                int driverNumber = (driverNumberParam == null || driverNumberParam.length() == 0)
                        ? 0 : Integer.parseInt(driverNumberParam);
                userService.createUser(userNameParam, DigestUtils.md5Hex(userPasswordParam), userRole, driverNumber);
                request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.USER_IS_CREATED);
                response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
            }
            break;
            case ActionConstants.PERFORM_EDITING_ACTION: {
                String userNameParam = request.getParameter(ParamConstants.USER_NAME_PARAM);
                String userPasswordParam = request.getParameter(ParamConstants.USER_PASSWORD_PARAM);
                UserRole userRole = getUserRole(request.getParameter(ParamConstants.USER_ROLE_PARAM));
                userService.changeByName(userNameParam, DigestUtils.md5Hex(userPasswordParam), userRole);
                request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.USER_IS_EDITED);
                response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
            }
            break;
            case ActionConstants.PERFORM_DELETING_ACTION: {
                String userNameParam = request.getParameter(ParamConstants.USER_NAME_PARAM);
                userService.deleteByName(userNameParam);
                request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.USER_IS_DELETED);
                response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_ADMIN_PAGE);
            }
            break;
            case ActionConstants.PERFORM_USER_EDITING_ACTION: {
                String userNameParam = request.getParameter(ParamConstants.USER_NAME_PARAM);
                String userPasswordParam = request.getParameter(ParamConstants.USER_PASSWORD_PARAM);
                userService.changePasswordByName(userNameParam, DigestUtils.md5Hex(userPasswordParam));
                request.getSession().setAttribute(ParamConstants.SUCCESS_MESSAGE_PARAM, MessageConstants.PASSWORD_IS_EDITED);
                response.sendRedirect(request.getContextPath() + PageConstants.CONFIRMATION_USER_PAGE);
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
