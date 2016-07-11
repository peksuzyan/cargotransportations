<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="app_languages" var="appLanguage"/>
<spring:message code="app_button_en" var="appButtonEn"/>
<spring:message code="app_button_ru" var="appButtonRu"/>

<%--
<h3>
    <a href="/cargoes?lang=en">${appButtonEn}</a>
    <a>|</a>
    <a href="/cargoes?lang=ru">${appButtonRu}</a>
</h3>
--%>

<div class="panel panel-default">
    <div class="panel-heading">${appLanguage}</div>
    <div class="panel-body"><a href="/cargoes?lang=en">${appButtonEn}</a></div>
    <div class="panel-body"><a href="/cargoes?lang=ru">${appButtonRu}</a></div>
</div>
