package com.tsystems.cargotransportations.presentation.logging;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoggingFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (httpServletRequest.getHeaderNames().hasMoreElements()) {
            Object obj = httpServletRequest.getHeaderNames().nextElement();
            //System.out.println("SERVER LOGS: header name -> " + obj);
            //System.out.println("SERVER LOGS: header value -> " + httpServletRequest.getHeader(obj.toString()));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        this.config = null;
    }

}
