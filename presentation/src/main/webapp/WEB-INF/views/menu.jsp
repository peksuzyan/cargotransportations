<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="app_categories" var="appCategories" />
<spring:message code="title_cargoes" var="titleCargoes" />
<spring:message code="title_drivers" var="titleDrivers" />
<spring:message code="title_routes" var="titleRoutes" />
<spring:message code="title_trucks" var="titleTrucks" />
<spring:message code="title_orders" var="titleOrders" />
<spring:message code="title_users" var="titleUsers" />

<h3>${appCategories}</h3>
<a href="/${titleCargoes}"><h3>${titleCargoes}</h3></a>
<a href="/${titleDrivers}"><h3>${titleDrivers}</h3></a>
<a href="/${titleTrucks}"><h3>${titleTrucks}</h3></a>
<a href="/${titleOrders}"><h3>${titleOrders}</h3></a>
<a href="/${titleRoutes}"><h3>${titleRoutes}</h3></a>
<a href="/${titleUsers}"><h3>${titleUsers}</h3></a>