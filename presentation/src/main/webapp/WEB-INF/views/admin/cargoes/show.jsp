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
<spring:message code="placeholder_departure_city" var="placeholderDepartureCity"/>
<spring:message code="placeholder_arrival_city" var="placeholderArrivalCity"/>
<spring:message code="placeholder_weight" var="placeholderWeight"/>
<spring:message code="placeholder_cargo_name" var="placeholderCargoName"/>
<spring:message code="confirm_cargo_deleting_text" var="confirmCargoDeletingText" />

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
<c:set var="errorsClass" value="control-label col-lg-12 text-danger" />
<c:set var="buttonClass" value="btn btn-default" />
<c:set var="buttonWideClass" value="btn btn-default btn-block" />
<c:set var="cargoCheckingButtons" value="cargo-checking-buttons" />
<c:set var="cargoCheckingResult" value="cargo-checking-result" />

<spring:url var="checkURL" value="/cargoes/${cargo.id}?check"  />
<spring:url var="deleteURL" value="/cargoes/${cargo.id}?delete" />
<spring:url var="cancelURL" value="/cargoes" />

<c:if test="${message.type eq 'error'}">
    <div class="alert alert-danger"><strong>${message.entry}</strong></div>
</c:if>

<form:form method="post" modelAttribute="cargo" cssClass="${formClass}" role="form" id="cargoForm">

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
                        value="${cargo.name}" placeholder="${placeholderCargoName}"
                        name="${name}" required="true"/>
        </div>
        <form:errors path="${name}" cssClass="${errorsClass}" for="${name}"/>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${weight}" cssClass="${labelClass}">${cargoWeight}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${weight}" cssClass="${inputClass}"
                        value="${cargo.weight}" placeholder="${placeholderWeight}"
                        name="${weight}" required="true"/>
        </div>
        <form:errors path="${weight}" cssClass="${errorsClass}" for="${weight}" />
    </div>

    <div class="${outerDivClass}">
        <form:label path="${depCity}" cssClass="${labelClass}">${cargoDepartureCity}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${depCity}" cssClass="${inputClass}"
                        value="${cargo.departureCity}" placeholder="${placeholderDepartureCity}"
                        name="${depCity}" required="true"/>
        </div>
        <div><form:errors path="${depCity}" cssClass="${errorsClass}" for="${depCity}" /></div>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${arCity}" cssClass="${labelClass}">${cargoArrivalCity}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${arCity}" cssClass="${inputClass}"
                        value="${cargo.arrivalCity}" placeholder="${placeholderArrivalCity}"
                        name="${arCity}" required="true"/>
        </div>
        <div><form:errors path="${arCity}" cssClass="${errorsClass}" for="${arCity}" /></div>
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
            <button class="${buttonClass} ${cargoCheckingButtons}" type="submit">${appButtonSave}</button>
            <c:if test="${cargo.id != 0}">
                <a class="${buttonClass} ${cargoCheckingButtons}" type="button"
                    <%--id="delete_button"--%> data-toggle="modal" data-target="#deleting">${appButtonDelete}</a>
                <a class="${buttonClass}" type="button"
                   href="${cancelURL}">${appButtonCancel}</a>
            </c:if>
            <c:if test="${cargo.id == 0}">
                <button class="${buttonClass}" data-dismiss="modal">${appButtonCancel}</button>
            </c:if>
        </div>
    </div>

    <c:if test="${cargo.id != 0}">
        <div class="${outerDivClass}">
            <div class="${buttonDivClass}">
                    <%--<span id="checking_result" class="label label-danger" hidden></span>--%>
                <h3 id="${cargoCheckingResult}" class="label label-danger" hidden></h3>
            </div>
        </div>
    </c:if>

</form:form>

<c:if test="${cargo.id != 0}">
    <div id="deleting" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body" align="center">
                    <div class="row">
                        <div class="col-lg-12">
                            <label>${confirmCargoDeletingText}</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <form method="post" action="${cargo.id}?delete">
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

        $('.${cargoCheckingButtons}').click(function(event){
            if (!isLaunched) {
                $('.${cargoCheckingButtons}').attr("disabled", true);
                $.ajax({
                    url: '${checkURL}',
                    type: 'GET',
                    dataType: 'json',
                    contentType: 'application/json'
                }).done(function(response) {
                    if (response.type == 'passed') {
                        $('.${cargoCheckingButtons}').attr("disabled", false);
                        isChecked = true;
                        event.target.click();
                    } else {
                        $("#${cargoCheckingResult}").text(response.entry).show();
                    }
                });
                isLaunched = true;
            }
            if (!isChecked || !isLaunched) event.stopPropagation();
        });
    </script>
</c:if>

<script>
    $('#cargoForm').validate();
</script>
