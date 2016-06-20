package com.tsystems.cargotransportations.presentation.authentication;

import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;
import com.tsystems.cargotransportations.service.abstracts.UserService;
import com.tsystems.cargotransportations.service.implementations.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.MagicConstants.HALF_HOUR;
import static com.tsystems.cargotransportations.constant.MessageConstants.WRONG_USERNAME_OR_PASSWORD;
import static com.tsystems.cargotransportations.constant.PageConstants.*;
import static com.tsystems.cargotransportations.constant.ParamConstants.*;

/**
 * Authenticates clients by user name and password.
 */
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter(USER_NAME_PARAM);
        String userPassword = request.getParameter(USER_PASSWORD_PARAM);
        User user = userService.getByName(userName);
        if (user != null && user.getPassword().equals(userPassword)) {
            HttpSession session = request.getSession();
            session.setAttribute(USER_NAME_PARAM, userName);
            UserRole userRole = user.getUserRole();
            session.setAttribute(USER_ROLE_PARAM, userRole);
            session.setMaxInactiveInterval(HALF_HOUR);
            response.sendRedirect(request.getContextPath() +
                    (userRole == UserRole.ADMIN ? WELCOME_ADMIN_PAGE : WELCOME_USER_PAGE));
        } else {
            request.setAttribute(ERROR_MESSAGE_PARAM, WRONG_USERNAME_OR_PASSWORD);
            getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
