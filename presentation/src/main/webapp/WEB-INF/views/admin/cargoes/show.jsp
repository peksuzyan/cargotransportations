<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="title_passport_cargo" var="titleCargoPassport" />
<spring:message code="cargo_number" var="cargoNumber" />
<spring:message code="cargo_name" var="cargoName" />
<spring:message code="cargo_weight" var="cargoWeight" />
<spring:message code="cargo_departure_city" var="cargoDepartureCity" />
<spring:message code="cargo_arrival_city" var="cargoArrivalCity" />
<spring:message code="cargo_status" var="cargoStatus" />
<spring:message code="app_button_save" var="appButtonSave" />
<spring:message code="app_button_cancel" var="appButtonCancel" />

<c:set var="number" value="number" />
<c:set var="name" value="name" />
<c:set var="weight" value="weight" />
<c:set var="depCity" value="departureCity" />
<c:set var="arCity" value="arrivalCity" />
<c:set var="status" value="status" />

<h2>${titleCargoPassport}</h2>

<form:form method="post" modelAttribute="cargo">

    <form:label path="${number}" >${cargoNumber}:</form:label>
    <form:input path="${number}" value="${cargo.number}" />
    <div><form:errors path="${number}" /></div>

    <form:label path="${name}" >${cargoName}:</form:label>
    <form:input path="${name}" value="${cargo.name}" />
    <div><form:errors path="${name}" /></div>

    <form:label path="${weight}" >${cargoWeight}:</form:label>
    <form:input path="${weight}" value="${cargo.weight}" />
    <div><form:errors path="${weight}" /></div>

    <form:label path="${depCity}" >${cargoDepartureCity}:</form:label>
    <form:input path="${depCity}" value="${cargo.departureCity}" />
    <div><form:errors path="${depCity}" /></div>

    <form:label path="${arCity}" >${cargoArrivalCity}:</form:label>
    <form:input path="${arCity}" value="${cargo.arrivalCity}" />
    <div><form:errors path="${arCity}" /></div>

    <form:label path="${status}" >${cargoStatus}:</form:label>
    <form:input path="${status}" value="${cargo.status}" />
    <div><form:errors path="${status}" /></div>

    <button type="submit">${appButtonSave}</button>
    <button type="reset">${appButtonCancel}</button>

</form:form>