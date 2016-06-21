package com.tsystems.cargotransportations.presentation.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.tsystems.cargotransportations.constants.PageConstants.LOGIN_PAGE;

/**
 * Filter tests a session and sends to authentication if session isn't valid.
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        if (session == null && !uri.endsWith(LOGIN_PAGE)){
            response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
