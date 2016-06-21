package com.tsystems.cargotransportations.presentation.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.tsystems.cargotransportations.constants.ActionConstants.ADD_ACTION;
import static com.tsystems.cargotransportations.constants.ActionConstants.NOTHING_ACTION;
import static com.tsystems.cargotransportations.constants.MessageConstants.ACTION_IS_NOT_EXISTED;
import static com.tsystems.cargotransportations.constants.PageConstants.CONFIRMATION_ADMIN_PAGE;
import static com.tsystems.cargotransportations.constants.ParamConstants.ACTION_PARAM;
import static com.tsystems.cargotransportations.constants.ParamConstants.ERROR_MESSAGE_PARAM;

/**
 * Common abstract parent of all entities servlets.
 */
abstract class EntityServlet<T> extends HttpServlet {
    /**
     * Processes by ordinary way of refresh action with given parameters.
     * @param request http request
     * @param response http response
     * @param entityListParam param name of entity list
     * @param entitiesListPage page of entities list
     * @param entities entities
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    void processRefresh(HttpServletRequest request,
                        HttpServletResponse response,
                        String entityListParam,
                        String entitiesListPage,
                        List<T> entities) throws ServletException, IOException {
        request.setAttribute(entityListParam, entities);
        getServletContext().getRequestDispatcher(entitiesListPage).forward(request, response);
    }

    /**
     * Processes by ordinary way of add action with given parameters.
     * @param request http request
     * @param response http response
     * @param entityRegistrationPage page of entity registration
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    void processAdd(HttpServletRequest request,
                    HttpServletResponse response,
                    String entityRegistrationPage) throws ServletException, IOException {
        request.setAttribute(ACTION_PARAM, ADD_ACTION);
        getServletContext().getRequestDispatcher(entityRegistrationPage).forward(request, response);
    }

    /**
     * Processes by ordinary way situation when action isn't existed.
     * @param request http request
     * @param response http response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    void processDefault(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ACTION_IS_NOT_EXISTED);
        response.sendRedirect(request.getContextPath() + CONFIRMATION_ADMIN_PAGE);
    }

    /**
     * Validates action param.
     * @param request http request
     * @return action param
     */
    String getActionParam(HttpServletRequest request) {
        String actionParam = request.getParameter(ACTION_PARAM);
        return (actionParam == null ? NOTHING_ACTION : actionParam);
    }
}
