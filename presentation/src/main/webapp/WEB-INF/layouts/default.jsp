<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<spring:message code="app_name" var="appName" />

<%-- jQuery and jQueryUI --%>
<spring:url value="/resources/scripts/jquery-3.0.0.js" var="jquery_url" />
<spring:url value="/resources/scripts/jquery-ui.min.js" var="jquery_ui_url" />
<spring:url value="/resources/styles/jquery-ui.css" var="jquery_ui_css" />
<spring:url value="/resources/styles/jquery-ui.theme.css" var="jquery_ui_theme_css" />
<spring:url value="/resources/styles/jquery-ui.structure.css" var="jquery_ui_structure_css" />
<spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrap_css" />

<%-- jqGrid --%>
<spring:url value="/resources/jqgrid/js/i18n/grid.locale-en.js" var="jqgrid_locale_en_url" />
<spring:url value="/resources/jqgrid/js/i18n/grid.locale-ru.js" var="jqgrid_locale_ru_url" />
<spring:url value="/resources/jqgrid/js/jquery.jqGrid.min.js" var="jqgrid_url" />
<spring:url value="/resources/jqgrid/css/ui.jqgrid-bootstrap.css" var="jqgrid_css" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=8" />

        <link rel="stylesheet" type="text/css" media="screen" href="${jquery_ui_css}" />
        <link rel="stylesheet" type="text/css" media="screen" href="${jquery_ui_theme_css}" />
        <link rel="stylesheet" type="text/css" media="screen" href="${jquery_ui_structure_css}" />
        <link rel="stylesheet" type="text/css" media="screen" href="${jqgrid_css}" />
        <link rel="stylesheet" type="text/css" media="screen" href="${bootstrap_css}"/>

        <script src="${jquery_url}" type="text/javascript"></script>
        <script src="${jquery_ui_url}" type="text/javascript"></script>
        <script src="${jqgrid_url}" type="text/javascript"></script>
        <script src="${jqgrid_locale_ru_url}" type="text/javascript"></script>
        <script src="${jqgrid_locale_en_url}" type="text/javascript"></script>

        <title>${appName}</title>
    </head>
    <body>
<%--        <table>
            <tr>
                <td></td>
                <td>
                    <tiles:insertAttribute name="header" ignore="true" />
                </td>
            </tr>
            <tr>
               <td>
                   <tiles:insertAttribute name="menu" ignore="true" />
               </td>
               <td>
                   <tiles:insertAttribute name="body" />
               </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <tiles:insertAttribute name="footer" ignore="true" />
                </td>
            </tr>
        </table>--%>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <tiles:insertAttribute name="header" ignore="true" />
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2">
                    <tiles:insertAttribute name="menu" ignore="true" />
                </div>
                <div class="col-lg-8">
                    <c:if test="${not empty message}">
                        <div class="row">
                            <div class="col-lg-12">
                                <tiles:insertAttribute name="message" ignore="true" />
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-lg-12">
                            <tiles:insertAttribute name="body" />
                        </div>
                    </div>
                </div>
                <div class="col-lg-2">
                    <tiles:insertAttribute name="footer" ignore="true" />
                </div>
            </div>
            <%--<div class="row">
                <div class="col-lg-12">

                </div>
            </div>--%>
        </div>
    </body>
</html>
