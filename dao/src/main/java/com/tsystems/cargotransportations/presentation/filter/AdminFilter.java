package com.tsystems.cargotransportations.presentation.filter;

import com.tsystems.cargotransportations.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constant.PageConstants.WELCOME_USER_PAGE;
import static com.tsystems.cargotransportations.constant.ParamConstants.USER_ROLE_PARAM;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (UserRole.ADMIN.toString().equals(request.getParameter(USER_ROLE_PARAM))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(request.getContextPath() + WELCOME_USER_PAGE);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
