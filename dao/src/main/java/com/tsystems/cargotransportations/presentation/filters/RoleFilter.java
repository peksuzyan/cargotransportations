package com.tsystems.cargotransportations.presentation.filters;

import com.tsystems.cargotransportations.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tsystems.cargotransportations.constants.MessageConstants.PERMISSION_DENIED;
import static com.tsystems.cargotransportations.constants.PageConstants.LOGIN_PAGE;
import static com.tsystems.cargotransportations.constants.ParamConstants.ERROR_MESSAGE_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.USER_ROLE_PARAM;

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
        Object userRoleParam = request.getSession().getAttribute(USER_ROLE_PARAM);
        if (userRole.toString().equals(userRoleParam.toString())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM, PERMISSION_DENIED);
            response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
