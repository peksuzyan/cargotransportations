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

<c:set var="categoryDivClass" value="col-sm-6 col-md-4 col-lg-12" />
<c:set var="hCategoryDivClass" value="col-sm-12 col-md-12 col-lg-12" />
<c:set var="categoryLinkClass" value="list-group-item" />

<div class="panel panel-default">
    <div class="panel-heading">${appCategories}</div>
</div>

<div class="list-group">
    <%--<div class="${categoryDivClass}">
        <a href="/cargoes" class="${categoryLinkClass}">${titleCargoes}</a>
    </div>
    <div class="${categoryDivClass}">
        <a href="/drivers" class="${categoryLinkClass}">${titleDrivers}</a>
    </div>
    <div class="${categoryDivClass}">
        <a href="/trucks" class="${categoryLinkClass}">${titleTrucks}</a>
    </div>
    <div class="${categoryDivClass}">
        <a href="/orders" class="${categoryLinkClass}">${titleOrders}</a>
    </div>
    <div class="${categoryDivClass}">
        <a href="/routes" class="${categoryLinkClass}">${titleRoutes}</a>
    </div>
    <div class="${categoryDivClass}">
        <a href="/users" class="${categoryLinkClass}">${titleUsers}</a>
    </div>--%>
    <a href="/cargoes" class="${categoryLinkClass}">${titleCargoes}</a>
    <a href="/drivers" class="${categoryLinkClass}">${titleDrivers}</a>
    <a href="/trucks" class="${categoryLinkClass}">${titleTrucks}</a>
    <a href="/orders" class="${categoryLinkClass}">${titleOrders}</a>
    <a href="/routes" class="${categoryLinkClass}">${titleRoutes}</a>
    <a href="/users" class="${categoryLinkClass}">${titleUsers}</a>
</div>

