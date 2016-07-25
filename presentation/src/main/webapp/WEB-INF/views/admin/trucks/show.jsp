<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="title_passport_truck" var="titleTruckPassport"/>
<spring:message code="truck_id" var="truckId"/>
<spring:message code="truck_number" var="truckNumber"/>
<spring:message code="truck_people" var="truckPeople"/>
<spring:message code="truck_active" var="truckActive"/>
<spring:message code="truck_capacity" var="truckCapacity"/>
<spring:message code="truck_city" var="truckCity"/>
<spring:message code="app_button_save" var="appButtonSave"/>
<spring:message code="app_button_cancel" var="appButtonCancel"/>
<spring:message code="app_button_delete" var="appButtonDelete"/>
<spring:message code="placeholder_number" var="placeholderNumber"/>
<spring:message code="placeholder_capacity" var="placeholderCapacity"/>
<spring:message code="placeholder_city" var="placeholderCity"/>
<spring:message code="confirm_truck_deleting_text" var="confirmTruckDeletingText"/>

<c:set var="id" value="id"/>
<c:set var="number" value="number"/>
<c:set var="people" value="people"/>
<c:set var="active" value="active"/>
<c:set var="capacity" value="capacity"/>
<c:set var="city" value="city"/>

<c:set var="formClass" value="form-horizontal"/>
<c:set var="outerDivClass" value="row form-group"/>
<%--<c:set var="innerDivClass" value="col-lg-4"/>
<c:set var="buttonDivClass" value="col-lg-offset-3 col-lg-9"/>
<c:set var="labelClass" value="control-label col-lg-3"/>
<c:set var="headerClass" value="col-lg-offset-1 col-lg-11"/>--%>
<c:set var="inputClass" value="form-control"/>
<c:set var="errorsClass" value="control-label text-danger"/>
<c:set var="buttonClass" value="btn btn-default"/>
<c:set var="buttonWideClass" value="btn btn-default btn-block"/>
<c:set var="truckCheckingButtons" value="truck-checking-buttons"/>
<c:set var="truckCheckingResult" value="truck-checking-result"/>

<c:set var="headerClass" value="col-lg-offset-4 col-lg-4
                                col-md-offset-4 col-md-4
                                col-sm-offset-3 col-sm-6
                                col-xs-offset-2 col-sm-8"/>
<c:set var="labelClass" value="control-label
                               col-lg-offset-1 col-lg-3
                               col-md-4
                               col-sm-4
                               col-xs-12"/>
<c:set var="innerDivClass" value="col-lg-4
                                  col-md-4
                                  col-sm-4
                                  col-xs-12"/>
<c:set var="buttonDivClass" value="col-xs-offset-4 col-xs-4"/>

<spring:url var="trucksURL" value="/admin/trucks"/>
<spring:url var="checkURL" value="${trucksURL}/${truck.id}?check"/>
<spring:url var="deleteURL" value="${trucksURL}/${truck.id}?delete"/>
<spring:url var="cancelURL" value="${trucksURL}"/>

<c:if test="${message.type eq 'error'}">
    <div class="alert alert-danger"><strong>${message.entry}</strong></div>
</c:if>

<form:form method="post" modelAttribute="truck" cssClass="${formClass}" role="form" id="truckForm">

    <div class="${outerDivClass}">
        <label class="${headerClass}">
            <h3>${titleTruckPassport}
                <c:if test="${truck.id != 0}"> <kbd>#${truck.id}</kbd></c:if>
            </h3>
        </label>
    </div>

    <c:if test="${truck.id != 0}">
        <div class="${outerDivClass}">
            <form:label path="${id}" cssClass="${labelClass}">${truckId}:</form:label>
            <div class="${innerDivClass}">
                <form:input path="${id}" cssClass="${inputClass}" value="${truck.id}" readonly="true"/>
            </div>
            <div><form:errors path="${id}" cssClass="${errorsClass}"/></div>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <form:label path="${number}" cssClass="${labelClass}">${truckNumber}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${number}" cssClass="${inputClass}"
                        value="${truck.number}" placeholder="${placeholderNumber}"
                        name="${number}" required="true"/>
        </div>
        <form:errors path="${number}" cssClass="${errorsClass}" for="${number}"/>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${people}" cssClass="${labelClass}">${truckPeople}:</form:label>
        <div class="${innerDivClass}">
            <form:select path="${people}" cssClass="${inputClass}" name="${people}">
                <form:option value="1">1</form:option>
                <form:option value="2">2</form:option>
                <form:option value="3">3</form:option>
            </form:select>
        </div>
        <form:errors path="${people}" cssClass="${errorsClass}" for="${people}"/>
    </div>

    <c:if test="${truck.id != 0}">
        <div class="${outerDivClass}">
            <form:label path="${active}" cssClass="${labelClass}">${truckActive}:</form:label>
            <div class="${innerDivClass}">
                <form:select path="${active}" cssClass="${inputClass}" name="${active}">
                    <form:option value="true">Yes</form:option>
                    <form:option value="false">No</form:option>
                </form:select>
            </div>
            <form:errors path="${active}" cssClass="${errorsClass}" for="${active}"/>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <form:label path="${capacity}" cssClass="${labelClass}">${truckCapacity}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${capacity}" cssClass="${inputClass}"
                        value="${truck.capacity}" placeholder="${placeholderCapacity}"
                        name="${capacity}" required="true"/>
        </div>
        <form:errors path="${capacity}" cssClass="${errorsClass}" for="${capacity}"/>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${city}" cssClass="${labelClass}">${truckCity}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${city}" cssClass="${inputClass}"
                        value="${truck.city}" required="true"/>
        </div>
        <form:errors path="${city}" cssClass="${errorsClass}"/>
    </div>

    <div class="${outerDivClass}">
        <div class="${buttonDivClass}">
            <button class="${buttonClass} ${truckCheckingButtons}" type="submit">${appButtonSave}</button>
            <c:if test="${truck.id != 0}">
                <a class="${buttonClass} ${truckCheckingButtons}" type="button"
                   data-toggle="modal" data-target="#deleting">${appButtonDelete}</a>
                <a class="${buttonClass}" type="button"
                   href="${cancelURL}">${appButtonCancel}</a>
            </c:if>
            <c:if test="${truck.id == 0}">
                <button class="${buttonClass}" data-dismiss="modal">${appButtonCancel}</button>
            </c:if>
        </div>
    </div>

</form:form>

<c:if test="${truck.id != 0}">
    <div id="deleting" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body" align="center">
                    <div class="row">
                        <div class="col-lg-12">
                            <label>${confirmTruckDeletingText}</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <form method="post" action="${truck.id}?delete">
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
</c:if>

<script>
    $('#truckForm').validate();
</script>
