<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="title_passport_order" var="titleOrderPassport"/>
<spring:message code="order_id" var="orderId"/>
<spring:message code="order_status" var="orderStatus"/>
<spring:message code="order_cargoes" var="orderCargoes"/>
<spring:message code="order_truck" var="orderTruck"/>
<spring:message code="order_drivers" var="orderDrivers"/>
<spring:message code="order_route" var="orderRoute"/>
<spring:message code="order_creation_date" var="orderCreationDate"/>
<spring:message code="order_select_cargo" var="selectCargo"/>
<spring:message code="order_select_truck" var="selectTruck"/>
<spring:message code="order_select_driver" var="selectDriver"/>
<spring:message code="order_select_route" var="selectRoute"/>
<spring:message code="app_button_save" var="appButtonSave"/>
<spring:message code="app_button_cancel" var="appButtonCancel"/>
<spring:message code="app_button_delete" var="appButtonDelete"/>
<spring:message code="app_button_add" var="appButtonAdd"/>
<spring:message code="app_button_clear" var="appButtonClear"/>
<spring:message code="confirm_order_deleting_text" var="confirmOrderDeletingText"/>

<c:set var="id" value="id"/>
<c:set var="status" value="status"/>
<c:set var="cargoes" value="cargoes"/>
<c:set var="truck" value="truck"/>
<c:set var="drivers" value="drivers"/>
<c:set var="route" value="route"/>
<c:set var="cDate" value="creationDate"/>

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

<c:set var="headerClass" value="col-lg-offset-3 col-lg-6
                                col-md-offset-3 col-md-6
                                col-sm-offset-2 col-sm-8
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

<spring:url var="ordersURL" value="/admin/orders"/>
<spring:url var="checkURL" value="${ordersURL}/${order.id}?check"/>
<spring:url var="deleteURL" value="${ordersURL}/${order.id}?delete"/>
<spring:url var="cancelURL" value="${ordersURL}"/>

<spring:url var="addCargoURL" value="${ordersURL}/cargo?add"/>
<spring:url var="clearCargoesURL" value="${ordersURL}/cargo?clear"/>
<spring:url var="addTruckURL" value="${ordersURL}/truck?add"/>
<spring:url var="clearTruckURL" value="${ordersURL}/truck?clear"/>
<spring:url var="addDriverURL" value="${ordersURL}/driver?add"/>
<spring:url var="clearDriversURL" value="${ordersURL}/driver?clear"/>
<spring:url var="addRouteURL" value="${ordersURL}/route?add"/>
<spring:url var="clearRouteURL" value="${ordersURL}/route?clear"/>

<c:if test="${message.type eq 'error'}">
    <div class="alert alert-danger"><strong>${message.entry}</strong></div>
</c:if>

<form method="post" class="${formClass}" id="orderForm">

    <div class="${outerDivClass}" align="center">
        <label class="${headerClass}">
            <h3>${titleOrderPassport} <kbd id="orderTitle">#${order.id}</kbd></h3>
        </label>
    </div>

    <div class="${outerDivClass}">
        <label for="${id}" class="${labelClass}">${orderId}:</label>
        <div class="${innerDivClass}">
            <input id="${id}" class="${inputClass}" value="${order.id}" readonly/>
        </div>
    </div>

    <c:if test="${order.id != 0}">
        <div class="${outerDivClass}">
            <label for="${status}" class="${labelClass}">${orderStatus}:</label>
            <div class="${innerDivClass}">
                <input id="${status}" class="${inputClass}" value="${order.status}" readonly/>
            </div>
        </div>

        <div class="${outerDivClass}">
            <label for="${cDate}" class="${labelClass}">${orderCreationDate}:</label>
            <div class="${innerDivClass}">
                <input id="${cDate}" class="${inputClass}" value="${order.creationDate}" readonly/>
            </div>
        </div>
    </c:if>

    <%-- CARGO SELECTION --%>
    <c:if test="${order.id == 0 || order.status eq 'OPEN'}">
        <div class="${outerDivClass}">
            <label for="inputCargoForm" class="${labelClass}">${selectCargo}:</label>
            <div class="${innerDivClass}">
                <select id="inputCargoForm" name="cargo" class="${inputClass}">
                    <option id="cargoSelectDefault">${selectCargo}...</option>
                    <c:forEach items="${suitable_cargoes}" var="cargo">
                        <option value="${cargo.id}">
                            ${cargo.weight} ${cargo.departureCity} - ${cargo.arrivalCity}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="${outerDivClass}">
            <div class="${buttonDivClass}">
                <button class="${buttonClass}" id="addCargoButton" onclick="addCargoToOrder()"
                        type="button">${appButtonAdd}</button>
                <button class="${buttonClass}" id="clearCargoesButton" onclick="clearCargoesOfOrder()"
                        type="button">${appButtonClear}</button>
                <h3 id="successInputCargoLabel" class="label label-success ajaxMessageShower" hidden></h3>
                <h3 id="dangerInputCargoLabel" class="label label-danger ajaxMessageShower" hidden></h3>
            </div>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <label for="outputCargoesForm" class="${labelClass}">${orderCargoes}:</label>
        <div class="${innerDivClass}">
            <textarea id="outputCargoesForm" class="${inputClass}" rows="3" readonly>
                <c:forEach items="${order.cargoes}" var="order_cargo">
                    ${order_cargo.weight}, ${order_cargo.departureCity} - ${order_cargo.arrivalCity} &#10
                </c:forEach>
            </textarea>
        </div>
    </div>
    <%-- CARGO SELECTION --%>

</form>

<c:if test="${order.id != 0}">
    <div id="deleting" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body" align="center">
                    <div class="row">
                        <div class="col-lg-12">
                            <label></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <form method="post" action="${order.id}?delete">
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
    var orderIdForm = $('#id.form-control');
    var addCargoButton = $('#addCargoButton');
    var clearCargoesButton = $('#clearCargoesButton');
    var outputCargoesForm = $('#outputCargoesForm');
    var inputCargoForm = $('#inputCargoForm');
    var cargoSelectDefault = $('#cargoSelectDefault');
    var successInputCargoLabel = $('#successInputCargoLabel');
    var dangerInputCargoLabel = $('#dangerInputCargoLabel');
    var allMessageLabels = $('.ajaxMessageShower');
    var orderTitle = $('#orderTitle');
    var lastAddedCargoId;

    function sendToCreatingOrder() {
        $.ajax({
            url: '/admin/orders/new',
            type: 'get',
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (response) {
            orderIdForm.val(response);
            orderTitle.text('#' + response);
            return true;
        })
    }

    function sendCargoToAdding() {
        $.ajax({
            url: '/admin/orders/cargo?add',
            type: 'post',
            dataType: 'json',
            data: {
                "orderId" : orderIdForm.val(),
                "cargoId" : inputCargoForm.val()
            }
        }).done(function (response) {
            if (response.type == 'passed') {
                var selectedInputCargoForm = $('#inputCargoForm option:selected');
                outputCargoesForm.val(outputCargoesForm.val() + selectedInputCargoForm.text() + '\n');
                selectedInputCargoForm.prop('disabled', 'true');
                lastAddedCargoId = outputCargoesForm.val();
                successInputCargoLabel.text(response.entry).show();
            } else {
                dangerInputCargoLabel.text(response.entry).show();
            }
            addCargoButton.removeAttr('disabled');
            clearCargoesButton.removeAttr('disabled');
        })
    }

    function addCargoToOrder() {
        allMessageLabels.hide();
        if (outputCargoesForm.val() == lastAddedCargoId) {
            addCargoButton.onblur();
            return;
        }
        sendCargoToAdding();
        addCargoButton.prop('disabled', 'true');
        clearCargoesButton.prop('disabled', 'true');
    }

    function sendToClearingCargoes() {
        $.ajax({
            url: '/admin/orders/cargo?clear',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: {
                order : orderIdForm.val()
            }
        }).done(function (response) {
            if (response.type == 'passed') {
                var selectedInputCargoForm = $('#inputCargoForm option:selected');
                outputCargoesForm.val('');
                selectedInputCargoForm.removeAttr('selected');
                cargoSelectDefault.prop('selected', 'true');
                successInputCargoLabel.text(response.entry).show();
            } else {
                dangerInputCargoLabel.text(response.entry).show();
            }
            addCargoButton.removeAttr('disabled');
            clearCargoesButton.removeAttr('disabled');
        })
    }

    function clearCargoesOfOrder() {
        allMessageLabels.hide();
        sendToClearingCargoes();
        addCargoButton.prop('disabled', 'true');
        clearCargoesButton.prop('disabled', 'true');
    }
</script>

<script>
    /*$('#truckForm').validate();*/
</script>
