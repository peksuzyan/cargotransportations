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
<spring:message code="placeholder" var="placeholder"/>

<c:set var="id" value="id" />
<c:set var="name" value="name" />
<c:set var="weight" value="weight" />
<c:set var="depCity" value="departureCity" />
<c:set var="arCity" value="arrivalCity" />
<c:set var="status" value="status" />

<c:set var="formClass" value="form-horizontal" />
<c:set var="outerDivClass" value="row form-group" />
<c:set var="innerDivClass" value="col-lg-4" />
<c:set var="buttonDivClass" value="col-lg-offset-3 col-lg-9" />
<c:set var="labelClass" value="control-label col-lg-3" />
<c:set var="headerClass" value="col-lg-offset-1 col-lg-11" />
<c:set var="inputClass" value="form-control" />
<c:set var="errorsClass" value="col-lg-5 text-danger" />
<c:set var="buttonClass" value="btn btn-default" />

<spring:url var="deleteURL" value="/cargoes/${cargo.id}?delete"  />
<spring:url var="cancelURL" value="/cargoes"  />

<%--<div class="row">
    <div class="col-lg-1"></div>
    <div class="col-lg-10">--%>

<form:form method="post" modelAttribute="cargo" cssClass="${formClass}" role="form">

    <div class="${outerDivClass}">
        <label class="${headerClass}">
            <h3>${titleCargoPassport}
                <c:if test="${cargo.id != 0}"> <kbd>#${cargo.id}</kbd></c:if>
            </h3>
        </label>
    </div>

<c:if test="${cargo.id != 0}">
    <div class="${outerDivClass}">
        <form:label path="${id}" cssClass="${labelClass}">${cargoId}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${id}" cssClass="${inputClass}" value="${cargo.id}" readonly="true" />
        </div>
        <div><form:errors path="${id}" cssClass="${errorsClass}" /></div>
    </div>
</c:if>

    <div class="${outerDivClass}">
        <form:label path="${name}" cssClass="${labelClass}">${cargoName}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${name}" cssClass="${inputClass}"
                        value="${cargo.name}" placeholder="${placeholder}" />
        </div>
        <form:errors path="${name}" cssClass="${errorsClass}" />
    </div>

    <div class="${outerDivClass}">
        <form:label path="${weight}" cssClass="${labelClass}">${cargoWeight}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${weight}" cssClass="${inputClass}"
                        value="${cargo.weight}" placeholder="${placeholder}" />
        </div>
        <form:errors path="${weight}" cssClass="${errorsClass}" />
    </div>

    <div class="${outerDivClass}">
        <form:label path="${depCity}" cssClass="${labelClass}">${cargoDepartureCity}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${depCity}" cssClass="${inputClass}"
                        value="${cargo.departureCity}" placeholder="${placeholder}" />
        </div>
        <div><form:errors path="${depCity}" cssClass="${errorsClass}" /></div>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${arCity}" cssClass="${labelClass}">${cargoArrivalCity}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${arCity}" cssClass="${inputClass}"
                        value="${cargo.arrivalCity}" placeholder="${placeholder}" />
        </div>
        <div><form:errors path="${arCity}" cssClass="${errorsClass}" /></div>
    </div>

<c:if test="${cargo.id != 0}">
    <div class="${outerDivClass}">
        <form:label path="${status}" cssClass="${labelClass}">${cargoStatus}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${status}" cssClass="${inputClass}" value="${cargo.status}" readonly="true" />
        </div>
        <div><form:errors path="${status}" cssClass="${errorsClass}" /></div>
    </div>
</c:if>

    <div class="${outerDivClass}">
        <div class="${buttonDivClass}">
            <button type="submit" class="${buttonClass}">${appButtonSave}</button>
        <c:if test="${cargo.id != 0}">
            <a class="${buttonClass}" href="${deleteURL}">${appButtonDelete}</a>
        </c:if>
            <a class="${buttonClass}" href="${cancelURL}">${appButtonCancel}</a>
        </div>
    </div>

</form:form>
