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
    <a href="/admin/cargoes" class="${languageClass}">${titleCargoes}</a>
    <a href="/admin/drivers" class="${languageClass}">${titleDrivers}</a>
    <a href="/admin/trucks" class="${languageClass}">${titleTrucks}</a>
    <a href="/admin/orders" class="${languageClass}">${titleOrders}</a>
    <a href="/admin/routes" class="${languageClass}">${titleRoutes}</a>
    <a href="/admin/users" class="${languageClass}">${titleUsers}</a>
</div>

