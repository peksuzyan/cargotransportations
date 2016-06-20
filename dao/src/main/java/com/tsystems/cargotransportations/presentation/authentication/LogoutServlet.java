package com.tsystems.cargotransportations.presentation.authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.PageConstants.LOGIN_PAGE;

/**
 * Invalidates session if exists.
 */
public class LogoutServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
    }
}
