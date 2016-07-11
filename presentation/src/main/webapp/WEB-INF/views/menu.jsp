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

<%--<div class="panel panel-default">
    <div class="panel-heading">${appCategories}</div>
    <div class="panel-body"><a href="/cargoes">${titleCargoes}</a></div>
    <div class="panel-body"><a href="/drivers">${titleDrivers}</a></div>
    <div class="panel-body"><a href="/trucks">${titleTrucks}</a></div>
    <div class="panel-body"><a href="/orders">${titleOrders}</a></div>
    <div class="panel-body"><a href="/routes">${titleRoutes}</a></div>
    <div class="panel-body"><a href="/users">${titleUsers}</a></div>
</div>--%>

<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            <a href="/cargoes">${titleCargoes}</a>
        </div>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            <a href="/drivers">${titleDrivers}</a>
        </div>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            <a href="/trucks">${titleTrucks}</a>
        </div>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            <a href="/orders">${titleOrders}</a>
        </div>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            <a href="/routes">${titleRoutes}</a>
        </div>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            <a href="/users">${titleUsers}</a>
        </div>
    </div>
</div>
