<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="title_passport_cargo" var="titleCargoPassport" />
<spring:message code="cargo_id" var="cargoId" />
<spring:message code="cargo_name" var="cargoName" />
<spring:message code="cargo_weight" var="cargoWeight" />
<spring:message code="cargo_departure_city" var="cargoDepartureCity" />
<spring:message code="cargo_arrival_city" var="cargoArrivalCity" />
<spring:message code="cargo_status" var="cargoStatus" />
<spring:message code="app_button_save" var="appButtonSave" />
<spring:message code="app_button_cancel" var="appButtonCancel" />
<spring:message code="app_button_delete" var="appButtonDelete" />

<c:set var="id" value="id" />
<c:set var="name" value="name" />
<c:set var="weight" value="weight" />
<c:set var="depCity" value="departureCity" />
<c:set var="arCity" value="arrivalCity" />
<c:set var="status" value="status" />

<h2>
    ${titleCargoPassport}<c:if test="${cargo.id != 0}"> <kbd>#${cargo.id}</kbd></c:if>
</h2>

<c:if test="${not empty message}">
    <c:if test="${message.type eq 'success'}"><div>${message.entry}</div></c:if>
    <c:if test="${message.type eq 'error'}"><div>${message.entry}</div></c:if>
</c:if>

<form:form method="post" modelAttribute="cargo">

    <c:if test="${cargo.id != 0}">
        <div>
            <form:label path="${id}" >${cargoId}:</form:label>
            <form:input path="${id}" value="${cargo.id}" readonly="true" />
            <div><form:errors path="${id}" cssClass="error" /></div>
        </div>
    </c:if>

    <div>
        <form:label path="${name}" >${cargoName}:</form:label>
        <form:input path="${name}" value="${cargo.name}" />
        <div><form:errors path="${name}" cssClass="error" /></div>
    </div>

    <div>
        <form:label path="${weight}" >${cargoWeight}:</form:label>
        <form:input path="${weight}" value="${cargo.weight}" />
        <div><form:errors path="${weight}" cssClass="error" /></div>
    </div>

    <div>
        <form:label path="${depCity}" >${cargoDepartureCity}:</form:label>
        <form:input path="${depCity}" value="${cargo.departureCity}" />
        <div><form:errors path="${depCity}" cssClass="error" /></div>
    </div>

    <div>
        <form:label path="${arCity}" >${cargoArrivalCity}:</form:label>
        <form:input path="${arCity}" value="${cargo.arrivalCity}" />
        <div><form:errors path="${arCity}" cssClass="error" /></div>
    </div>

    <c:if test="${cargo.id != 0}">
        <div>
            <form:label path="${status}" >${cargoStatus}:</form:label>
            <form:input path="${status}" value="${cargo.status}" readonly="true" />
            <div><form:errors path="${status}" cssClass="error"/></div>
        </div>
    </c:if>

    <button type="submit">${appButtonSave}</button>
    <c:if test="${cargo.id != 0}">
        <button><a href="/cargoes/${cargo.id}?delete">${appButtonDelete}</a></button>
    </c:if>
    <button><a href="/cargoes">${appButtonCancel}</a></button>


</form:form>