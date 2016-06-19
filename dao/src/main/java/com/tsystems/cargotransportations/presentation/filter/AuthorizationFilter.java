package com.tsystems.cargotransportations.presentation.filter;

import com.tsystems.cargotransportations.entity.User;
import com.tsystems.cargotransportations.entity.UserRole;
import com.tsystems.cargotransportations.service.UserService;
import com.tsystems.cargotransportations.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.tsystems.cargotransportations.constant.ParamConstants.USER_NAME_PARAM;
import static com.tsystems.cargotransportations.constant.ParamConstants.USER_ROLE_PARAM;

/**
 * Filter finds a user by user name, sets to the request user role as attribute and passes to next filter.
 */
public class AuthorizationFilter implements Filter {

    private UserService userService = new UserServiceImpl();

    private static final Map<String, UserRole> userRoles = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        UserRole userRole = userRoles.get(request.getParameter(USER_NAME_PARAM));
        request.setAttribute(USER_ROLE_PARAM, userRole);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        for (User user : userService.getAll()) {
            userRoles.put(user.getName(), user.getUserRole());
        }
    }

    @Override
    public void destroy() {}
}
