package com.tsystems.cargotransportations.presentation.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

import com.tsystems.cargotransportations.constants.FilterConstants;

/**
 * Filter that logs all request to the web application.
 */
public class RequestLoggingFilter implements Filter {
    /**
     * Logs events bound with received requests.
     */
    private static final Logger logger = Logger.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Enumeration params = request.getParameterNames();
        StringBuilder record = new StringBuilder();
        record.append(String.format(FilterConstants.RECORD_PATTERN, request.getRemoteAddr()));
        while (params.hasMoreElements()) {
            String name = (String) params.nextElement();
            String value = request.getParameter(name);
            record.append(String.format(FilterConstants.NAME_VALUE_PARAM_PATTERN, name, value));
        }
        logger.info(record);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info(FilterConstants.INITIALIZED);
    }

    @Override
    public void destroy() {
        logger.info(FilterConstants.DESTROYED);
    }
}
