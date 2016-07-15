<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="app_categories" var="appCategories"/>
<spring:message code="title_cargoes" var="titleCargoes"/>
<spring:message code="title_drivers" var="titleDrivers"/>
<spring:message code="title_routes" var="titleRoutes"/>
<spring:message code="title_trucks" var="titleTrucks"/>
<spring:message code="title_orders" var="titleOrders"/>
<spring:message code="title_users" var="titleUsers"/>

<c:set var="languageClass" value="list-group-item" />

<div class="list-group">
    <span class="${languageClass} list-group-item-warning">${appCategories}</span>
    <a href="/cargoes" class="${languageClass}">${titleCargoes}</a>
    <a href="/drivers" class="${languageClass}">${titleDrivers}</a>
    <a href="/trucks" class="${languageClass}">${titleTrucks}</a>
    <a href="/orders" class="${languageClass}">${titleOrders}</a>
    <a href="/routes" class="${languageClass}">${titleRoutes}</a>
    <a href="/users" class="${languageClass}">${titleUsers}</a>
</div>

