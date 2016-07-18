<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<spring:message code="app_name" var="appName" />

<%-- jQuery and jQueryUI --%>
<spring:url value="/resources/scripts/jquery-2.2.4.min.js" var="jquery_url" />
<spring:url value="/resources/scripts/jquery-ui.min.js" var="jquery_ui_url" />
<spring:url value="/resources/styles/jquery-ui.css" var="jquery_ui_css" />
<spring:url value="/resources/styles/jquery-ui.theme.css" var="jquery_ui_theme_css" />
<spring:url value="/resources/styles/jquery-ui.structure.css" var="jquery_ui_structure_css" />

<%-- jQuery Validation --%>
<spring:url value="/resources/scripts/jquery.validate.min.js" var="jquery_validate_url" />

<%-- Botstrap --%>
<spring:url value="/resources/bootstrap/css/bootstrap.min.css" var="bootstrap_css" />
<spring:url value="/resources/bootstrap/js/bootstrap.min.js" var="bootstrap_js" />

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" />

    <link rel="stylesheet" type="text/css" media="screen" href="${jquery_ui_css}" />
    <link rel="stylesheet" type="text/css" media="screen" href="${jquery_ui_theme_css}" />
    <link rel="stylesheet" type="text/css" media="screen" href="${jquery_ui_structure_css}" />
    <link rel="stylesheet" type="text/css" media="screen" href="${bootstrap_css}"/>

    <script src="${jquery_url}" type="text/javascript"></script>
    <script src="${jquery_ui_url}" type="text/javascript"></script>
    <script src="${jquery_validate_url}" type="text/javascript"></script>
    <script src="${bootstrap_js}" type="text/javascript"></script>

    <title>${appName}</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <tiles:insertAttribute name="header_user"/>
        </div>
    </div>
    <div class="row">
        <div class="
                    col-lg-offset-2 col-lg-8
                    col-lg-offset-2 col-md-8
                    col-sm-12
                    col-xs-12">
            <tiles:insertAttribute name="body_user"/>
        </div>
        <div class="
                    col-lg-2
                    col-md-2
                    col-sm-12
                    col-xs-12">
            <tiles:insertAttribute name="footer_user"/>
        </div>
    </div>
</div>
</body>
</html>