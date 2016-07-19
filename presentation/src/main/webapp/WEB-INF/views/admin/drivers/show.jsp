<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="title_passport_driver" var="titleDriverPassport"/>
<spring:message code="driver_id" var="driverId"/>
<spring:message code="driver_first_name" var="driverFirstName"/>
<spring:message code="driver_last_name" var="driverLastName"/>
<spring:message code="driver_name" var="driverName"/>
<spring:message code="driver_email" var="driverEmail"/>
<spring:message code="driver_city" var="driverCity"/>
<spring:message code="driver_status" var="driverStatus"/>
<spring:message code="driver_hours" var="driverHours"/>
<spring:message code="driver_truck" var="driverTruck"/>
<spring:message code="app_button_save" var="appButtonSave"/>
<spring:message code="app_button_cancel" var="appButtonCancel"/>
<spring:message code="app_button_delete" var="appButtonDelete"/>
<spring:message code="placeholder_first_name" var="placeholderFirstName"/>
<spring:message code="placeholder_last_name" var="placeholderLastName"/>
<spring:message code="placeholder_email" var="placeholderEmail"/>
<spring:message code="placeholder_city" var="placeholderCity"/>
<spring:message code="confirm_driver_deleting_text" var="confirmDriverDeletingText"/>

<c:set var="id" value="id"/>
<c:set var="fname" value="firstName"/>
<c:set var="lname" value="lastName"/>
<c:set var="email" value="email"/>
<c:set var="city" value="city"/>
<c:set var="status" value="status"/>
<c:set var="hours" value="hours"/>
<c:set var="truck" value="truck"/>

<c:set var="formClass" value="form-horizontal"/>
<c:set var="outerDivClass" value="row form-group"/>
<c:set var="innerDivClass" value="col-lg-4"/>
<c:set var="buttonDivClass" value="col-lg-offset-3 col-lg-9"/>
<c:set var="labelClass" value="control-label col-lg-3"/>
<c:set var="headerClass" value="col-lg-offset-1 col-lg-11"/>
<c:set var="inputClass" value="form-control"/>
<c:set var="errorsClass" value="control-label col-lg-12 text-danger"/>
<c:set var="buttonClass" value="btn btn-default"/>
<c:set var="buttonWideClass" value="btn btn-default btn-block"/>
<c:set var="driverCheckingButtons" value="driver-checking-buttons"/>
<c:set var="driverCheckingResult" value="driver-checking-result"/>

<spring:url var="driversURL" value="/admin/drivers"/>
<spring:url var="checkURL" value="${driversURL}/${driver.id}?check"/>
<spring:url var="deleteURL" value="${driversURL}/${driver.id}?delete"/>
<spring:url var="cancelURL" value="${driversURL}"/>

<c:if test="${message.type eq 'error'}">
    <div class="alert alert-danger"><strong>${message.entry}</strong></div>
</c:if>

<form:form method="post" modelAttribute="driver" cssClass="${formClass}" role="form" id="driverForm">

    <div class="${outerDivClass}">
        <label class="${headerClass}">
            <h3>${titleDriverPassport}
                <c:if test="${driver.id != 0}"> <kbd>#${driver.id}</kbd></c:if>
            </h3>
        </label>
    </div>

    <c:if test="${driver.id != 0}">
        <div class="${outerDivClass}">
            <form:label path="${id}" cssClass="${labelClass}">${driverId}:</form:label>
            <div class="${innerDivClass}">
                <form:input path="${id}" cssClass="${inputClass}" value="${driver.id}" readonly="true"/>
            </div>
            <div><form:errors path="${id}" cssClass="${errorsClass}"/></div>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <form:label path="${fname}" cssClass="${labelClass}">${driverFirstName}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${fname}" cssClass="${inputClass}"
                        value="${driver.firstName}" placeholder="${placeholderFirstName}"
                        name="${fname}"/>
        </div>
        <form:errors path="${fname}" cssClass="${errorsClass}" for="${fname}"/>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${lname}" cssClass="${labelClass}">${driverLastName}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${lname}" cssClass="${inputClass}"
                        value="${driver.lastName}" placeholder="${placeholderLastName}"
                        name="${lname}"/>
        </div>
        <form:errors path="${lname}" cssClass="${errorsClass}" for="${lname}"/>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${email}" cssClass="${labelClass}">${driverEmail}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${email}" cssClass="${inputClass}"
                        value="${driver.email}" placeholder="${placeholderEmail}"
                        name="${email}"/>
        </div>
        <div><form:errors path="${email}" cssClass="${errorsClass}" for="${email}"/></div>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${city}" cssClass="${labelClass}">${driverCity}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${city}" cssClass="${inputClass}"
                        value="${driver.city}" placeholder="${placeholderCity}"
                        name="${city}"/>
        </div>
        <div><form:errors path="${city}" cssClass="${errorsClass}" for="${city}"/></div>
    </div>

    <c:if test="${driver.id != 0}">
        <div class="${outerDivClass}">
            <form:label path="${status}" cssClass="${labelClass}">${driverStatus}:</form:label>
            <div class="${innerDivClass}">
                <form:input path="${status}" cssClass="${inputClass}" value="${driver.status}" readonly="true"/>
            </div>
            <div><form:errors path="${status}" cssClass="${errorsClass}"/></div>
        </div>
    </c:if>

    <c:if test="${driver.id != 0}">
        <div class="${outerDivClass}">
            <form:label path="${hours}" cssClass="${labelClass}">${driverHours}:</form:label>
            <div class="${innerDivClass}">
                <form:input path="${hours}" cssClass="${inputClass}" value="${driver.hours}" readonly="true"/>
            </div>
            <div><form:errors path="${hours}" cssClass="${errorsClass}"/></div>
        </div>
    </c:if>

    <c:if test="${driver.id != 0}">
        <div class="${outerDivClass}">
            <form:label path="${truck}" cssClass="${labelClass}">${driverTruck}:</form:label>
            <div class="${innerDivClass}">
                <form:input path="${truck}" cssClass="${inputClass}" value="${driver.truck.number}" readonly="true"/>
            </div>
            <div><form:errors path="${truck}" cssClass="${errorsClass}"/></div>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <div class="${buttonDivClass}">
            <button class="${buttonClass} ${driverCheckingButtons}" type="submit">${appButtonSave}</button>
            <c:if test="${driver.id != 0}">
                <a class="${buttonClass} ${driverCheckingButtons}" type="button"
                    <%--id="delete_button"--%> data-toggle="modal" data-target="#deleting">${appButtonDelete}</a>
                <a class="${buttonClass}" type="button"
                   href="${cancelURL}">${appButtonCancel}</a>
            </c:if>
            <c:if test="${driver.id == 0}">
                <button class="${buttonClass}" data-dismiss="modal">${appButtonCancel}</button>
            </c:if>
        </div>
    </div>

    <c:if test="${driver.id != 0}">
        <div class="${outerDivClass}">
            <div class="${buttonDivClass}">
                    <%--<span id="checking_result" class="label label-danger" hidden></span>--%>
                <h3 id="${driverCheckingResult}" class="label label-danger" hidden></h3>
            </div>
        </div>
    </c:if>

</form:form>

<c:if test="${driver.id != 0}">
    <div id="deleting" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body" align="center">
                    <div class="row">
                        <div class="col-lg-12">
                            <label>${confirmDriverDeletingText}</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <form method="post" action="${driver.id}?delete">
                                <button type="submit" class="${buttonWideClass}">${appButtonDelete}</button>
                                <button type="button" class="${buttonWideClass}"
                                        data-dismiss="modal">${appButtonCancel}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        var isChecked = false;
        var isLaunched = false;

        $('.${driverCheckingButtons}').click(function (event) {
            if (!isLaunched) {
                $('.${driverCheckingButtons}').attr("disabled", true);
                $.ajax({
                    url: '${checkURL}',
                    type: 'GET',
                    dataType: 'json',
                    contentType: 'application/json'
                }).done(function (response) {
                    if (response.type == 'passed') {
                        $('.${driverCheckingButtons}').attr("disabled", false);
                        isChecked = true;
                        event.target.click();
                    } else {
                        $("#${driverCheckingResult}").text(response.entry).show();
                    }
                });
                isLaunched = true;
            }
            if (!isChecked || !isLaunched) event.stopPropagation();
        });
    </script>
</c:if>

<script>
    /*$('#driverForm').validate();*/
</script>
