package com.tsystems.cargotransportations.presentation.filters;

import com.tsystems.cargotransportations.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tsystems.cargotransportations.constants.MessageConstants;
import com.tsystems.cargotransportations.constants.PageConstants;
import com.tsystems.cargotransportations.constants.ParamConstants;

/**
 * Abstract parent of all role filters.
 */
abstract class RoleFilter implements Filter {
    /**
     * User role that determines an access to pages which necessary to reach.
     */
    private UserRole userRole;

    RoleFilter(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object userRoleParam = request.getSession().getAttribute(ParamConstants.USER_ROLE_PARAM);
        if (userRole.toString().equals(userRoleParam.toString())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getSession().setAttribute(ParamConstants.ERROR_MESSAGE_PARAM, MessageConstants.PERMISSION_DENIED);
            response.sendRedirect(request.getContextPath() + PageConstants.LOGIN_PAGE);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
