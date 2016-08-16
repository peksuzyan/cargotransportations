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
<c:set var="inputClass" value="form-control"/>
<c:set var="errorsClass" value="control-label text-danger"/>
<c:set var="buttonClass" value="btn btn-default"/>
<c:set var="buttonWideClass" value="btn btn-default btn-block"/>
<c:set var="orderCheckingButtons" value="order-checking-buttons"/>
<c:set var="orderVerifyingButtons" value="order-verifying-buttons"/>
<c:set var="orderVerifyingResult" value="order-verifying-result"/>

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
<c:set var="buttonDivClass" value="col-xs-offset-4 col-xs-5"/>

<spring:url var="ordersURL" value="/admin/orders"/>
<spring:url var="checkURL" value="${ordersURL}/${order.id}?check"/>
<spring:url var="verifyURL" value="${ordersURL}/${order.id}?verify"/>
<spring:url var="deleteURL" value="${ordersURL}/${order.id}?delete"/>
<spring:url var="cancelURL" value="${ordersURL}"/>

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
            <input name="orderId" id="${id}" class="${inputClass}" value="${order.id}" readonly/>
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
                    <c:forEach items="${suitable_cargoes}" var="cargo">
                        <option value="${cargo.id}">${cargo.weight} ${cargo.departureCity} - ${cargo.arrivalCity}</option>
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
            <textarea id="outputCargoesForm" class="${inputClass}" rows="3" readonly><c:forEach items="${order.cargoes}" var="order_cargo"><c:out value="${order_cargo.weight}, ${order_cargo.departureCity} - ${order_cargo.arrivalCity}"/></c:forEach></textarea>
        </div>
    </div>
    <%-- CARGO SELECTION --%>

    <%-- TRUCK SELECTION --%>
    <c:if test="${order.id == 0 || order.status eq 'OPEN'}">
        <div class="${outerDivClass}">
            <label for="inputTruckForm" class="${labelClass}">${selectTruck}:</label>
            <div class="${innerDivClass}">
                <select id="inputTruckForm" name="truck" class="${inputClass}">
                    <c:forEach items="${suitable_trucks}" var="truck_">
                        <option value="${truck_.id}">${truck_.number}, ${truck_.capacity}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="${outerDivClass}">
            <div class="${buttonDivClass}">
                <button class="${buttonClass}" id="addTruckButton" onclick="addTruckToOrder()"
                        type="button">${appButtonAdd}</button>
                <button class="${buttonClass}" id="clearTrucksButton" onclick="clearTrucksOfOrder()"
                        type="button">${appButtonClear}</button>
                <h3 id="successInputTruckLabel" class="label label-success ajaxMessageShower" hidden></h3>
                <h3 id="dangerInputTruckLabel" class="label label-danger ajaxMessageShower" hidden></h3>
            </div>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <label for="outputTrucksForm" class="${labelClass}">${orderTruck}:</label>
        <div class="${innerDivClass}">
            <input id="outputTrucksForm" class="${inputClass}" type="text"
                   <c:if test="${order.truck ne null}">value="${order.truck.number}, ${order.truck.capacity}"</c:if>
                   readonly />
        </div>
    </div>
    <%-- TRUCK SELECTION --%>

    <%-- DRIVER SELECTION --%>
    <c:if test="${order.id == 0 || order.status eq 'OPEN'}">
        <div class="${outerDivClass}">
            <label for="inputDriverForm" class="${labelClass}">${selectDriver}:</label>
            <div class="${innerDivClass}">
                <select id="inputDriverForm" name="driver" class="${inputClass}">
                    <c:forEach items="${suitable_drivers}" var="driver">
                        <option value="${driver.id}">${driver.firstName} ${driver.lastName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="${outerDivClass}">
            <div class="${buttonDivClass}">
                <button class="${buttonClass}" id="addDriverButton" onclick="addDriverToOrder()"
                        type="button">${appButtonAdd}</button>
                <button class="${buttonClass}" id="clearDriversButton" onclick="clearDriversOfOrder()"
                        type="button">${appButtonClear}</button>
                <h3 id="successInputDriverLabel" class="label label-success ajaxMessageShower" hidden></h3>
                <h3 id="dangerInputDriverLabel" class="label label-danger ajaxMessageShower" hidden></h3>
            </div>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <label for="outputDriversForm" class="${labelClass}">${orderDrivers}:</label>
        <div class="${innerDivClass}">
            <textarea id="outputDriversForm" class="${inputClass}" rows="3" readonly><c:forEach items="${order.drivers}" var="order_driver"><c:out value="${order_driver.firstName} ${order_driver.lastName}"/></c:forEach></textarea>
        </div>
    </div>
    <%-- DRIVER SELECTION --%>

    <%-- ROUTE SELECTION --%>
    <c:if test="${order.id == 0 || order.status eq 'OPEN'}">
        <div class="${outerDivClass}">
            <label for="inputRouteForm" class="${labelClass}">${selectRoute}:</label>
            <div class="${innerDivClass}">
                <select id="inputRouteForm" name="route" class="${inputClass}">
                    <c:forEach items="${suitable_routes}" var="routeTemp">
                        <option value="${routeTemp.id}">${order.route.id}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="${outerDivClass}">
            <div class="${buttonDivClass}">
                <button class="${buttonClass}" id="addRouteButton" onclick="addRouteToOrder()"
                        type="button">${appButtonAdd}</button>
                <button class="${buttonClass}" id="clearRoutesButton" onclick="clearRoutesOfOrder()"
                        type="button">${appButtonClear}</button>
                <h3 id="successInputRouteLabel" class="label label-success ajaxMessageShower" hidden></h3>
                <h3 id="dangerInputRouteLabel" class="label label-danger ajaxMessageShower" hidden></h3>
            </div>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <label for="outputRoutesForm" class="${labelClass}">${orderRoute}:</label>
        <div class="${innerDivClass}">
            <input id="outputRoutesForm" class="${inputClass}" type="text"
                   <c:if test="${order.route ne null}">value="${order.route.id}"</c:if>
                   readonly/>
        </div>
    </div>
    <%-- ROUTE SELECTION --%>

    <div class="${outerDivClass}">
        <div class="${buttonDivClass}">
            <c:if test="${order.id == 0}">
                <button id="submitButton" class="${buttonClass} ${orderVerifyingButtons}" type="submit">${appButtonSave}</button>
            </c:if>
            <c:if test="${order.id != 0}">
                <button formaction="?add" class="${buttonClass} ${orderVerifyingButtons}" type="submit">${appButtonSave}</button>
                <a class="${buttonClass} ${orderCheckingButtons}" type="button"
                   data-toggle="modal" data-target="#deleting">${appButtonDelete}</a>
                <a class="${buttonClass}" type="button"
                   href="${cancelURL}">${appButtonCancel}</a>
            </c:if>
            <c:if test="${order.id == 0}">
                <button class="${buttonClass}" data-dismiss="modal">${appButtonCancel}</button>
            </c:if>
        </div>
    </div>

    <c:if test="${order.id != 0}">
        <div class="${outerDivClass}">
            <div class="${buttonDivClass}">
                <h3 id="${orderVerifyingResult}" class="label label-danger" hidden></h3>
            </div>
        </div>
    </c:if>

</form>

<c:if test="${order.id != 0}">
    <div id="deleting" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body" align="center">
                    <div class="row">
                        <div class="col-lg-12">
                            <label>${confirmOrderDeletingText}</label>
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
    var isChecked_Check = false;
    var isLaunched_Check = false;

    $('.${orderCheckingButtons}').click(function (event) {
        if (!isLaunched_Check) {
            $('.${orderCheckingButtons}').attr("disabled", true);
            $.ajax({
                url: '${checkURL}',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json'
            }).done(function (response) {
                if (response.type == 'passed') {
                    $('.${orderCheckingButtons}').attr("disabled", false);
                    isChecked_Check = true;
                    event.target.click();
                } else {
                    $("#${orderVerifyingResult}").text(response.entry).show();
                }
            });
            isLaunched_Check = true;
        }
        if (!isChecked_Check || !isLaunched_Check) event.stopPropagation();
    });
</script>

<script>
    var isChecked_Verify = false;
    var isLaunched_Verify = false;
    var verifyUrl = '${verifyURL}';

    $('.${orderVerifyingButtons}').click(function (event) {
        if (!isLaunched_Verify) {
            $('.${orderVerifyingButtons}').attr("disabled", true);
            $.ajax({
                url: verifyUrl,
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json'
            }).done(function (response) {
                if (response.type == 'passed') {
                    $('.${orderVerifyingButtons}').removeAttr("disabled");
                    isChecked_Verify = true;
                    event.target.click();
                } else {
                    $("#${orderVerifyingResult}").text(response.entry).show();
                }
            });
            isLaunched_Verify = true;
        }
        if (!isChecked_Verify || !isLaunched_Verify) event.stopPropagation();
    });
</script>

<script>
    var orderTitle = $('#orderTitle');
    var orderIdForm = $('#id.form-control');
    var allMessageLabels = $('.ajaxMessageShower');
    var submitButton = $('#submitButton');

    function sendToCreatingOrder() {
        $.ajax({
            url: '/admin/orders/new',
            type: 'get',
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (response) {
            orderIdForm.val(response);
            orderTitle.text('#' + orderIdForm.val());
            submitButton.attr("formaction", "/admin/orders/" + orderIdForm.val() + "?add");
            verifyUrl = '/admin/orders/' + orderIdForm.val() + '?verify';
            return true;
        })
    }

    /* ROUTE */
    var addRouteButton = $('#addRouteButton');
    var clearRoutesButton = $('#clearRoutesButton');
    var outputRoutesForm = $('#outputRoutesForm');
    var inputRouteForm = $('#inputRouteForm');
    var successInputRouteLabel = $('#successInputRouteLabel');
    var dangerInputRouteLabel = $('#dangerInputRouteLabel');
    var lastAddedRouteId;

    function sendRouteToAdding() {
        $.ajax({
            url: '/admin/orders/route?add',
            type: 'post',
            dataType: 'json',
            data: {
                "orderId" : orderIdForm.val(),
                "routeId" : inputRouteForm.val()
            }
        }).done(function (response) {
            if (response.type == 'passed') {
                var selectedInputRouteForm = $('#inputRouteForm option:selected');
                outputRoutesForm.val(selectedInputRouteForm.text());
                selectedInputRouteForm.prop('disabled', 'true');
                lastAddedRouteId = inputRouteForm.val();
                successInputRouteLabel.text(response.entry).show();
            } else {
                dangerInputRouteLabel.text(response.entry).show();
            }
            addRouteButton.removeAttr('disabled');
            clearRoutesButton.removeAttr('disabled');
        })
    }

    function addRouteToOrder() {
        allMessageLabels.hide();
        if (inputRouteForm.val() != lastAddedRouteId) {
            sendRouteToAdding();
            addRouteButton.prop('disabled', 'true');
            clearRoutesButton.prop('disabled', 'true');
        }
    }

    function sendToClearingRoutes() {
        $.ajax({
            url: '/admin/orders/route?clear',
            type: 'post',
            dataType: 'json',
            data: {
                "orderId" : orderIdForm.val()
            }
        }).done(function (response) {
            if (response.type == 'passed') {
                outputRoutesForm.val('');
                successInputRouteLabel.text(response.entry).show();
                $('#inputRouteForm option').removeAttr('disabled');
            } else {
                dangerInputRouteLabel.text(response.entry).show();
            }
            addRouteButton.removeAttr('disabled');
            clearRoutesButton.removeAttr('disabled');
        })
    }

    function clearRoutesOfOrder() {
        allMessageLabels.hide();
        sendToClearingRoutes();
        addRouteButton.prop('disabled', 'true');
        clearRoutesButton.prop('disabled', 'true');
    }

    /* TRUCK */
    var addTruckButton = $('#addTruckButton');
    var clearTrucksButton = $('#clearTrucksButton');
    var outputTrucksForm = $('#outputTrucksForm');
    var inputTruckForm = $('#inputTruckForm');
    var successInputTruckLabel = $('#successInputTruckLabel');
    var dangerInputTruckLabel = $('#dangerInputTruckLabel');
    var lastAddedTruckId;

    function sendTruckToAdding() {
        $.ajax({
            url: '/admin/orders/truck?add',
            type: 'post',
            dataType: 'json',
            data: {
                "orderId" : orderIdForm.val(),
                "truckId" : inputTruckForm.val()
            }
        }).done(function (response) {
            if (response.type == 'passed') {
                var selectedInputTruckForm = $('#inputTruckForm option:selected');
                outputTrucksForm.val(selectedInputTruckForm.text());
                selectedInputTruckForm.prop('disabled', 'true');
                lastAddedTruckId = inputTruckForm.val();
                successInputTruckLabel.text(response.entry).show();
            } else {
                dangerInputTruckLabel.text(response.entry).show();
            }
            addTruckButton.removeAttr('disabled');
            clearTrucksButton.removeAttr('disabled');
        })
    }

    function addTruckToOrder() {
        allMessageLabels.hide();
        if (inputTruckForm.val() != lastAddedTruckId) {
            sendTruckToAdding();
            addTruckButton.prop('disabled', 'true');
            clearTrucksButton.prop('disabled', 'true');
        }
    }

    function sendToClearingTrucks() {
        $.ajax({
            url: '/admin/orders/truck?clear',
            type: 'post',
            dataType: 'json',
            data: {
                "orderId" : orderIdForm.val()
            }
        }).done(function (response) {
            if (response.type == 'passed') {
                outputTrucksForm.val('');
                successInputTruckLabel.text(response.entry).show();
                $('#inputTruckForm option').removeAttr('disabled');
            } else {
                dangerInputTruckLabel.text(response.entry).show();
            }
            addTruckButton.removeAttr('disabled');
            clearTrucksButton.removeAttr('disabled');
        })
    }

    function clearTrucksOfOrder() {
        allMessageLabels.hide();
        sendToClearingTrucks();
        addTruckButton.prop('disabled', 'true');
        clearTrucksButton.prop('disabled', 'true');
    }

    /* DRIVER */
    var addDriverButton = $('#addDriverButton');
    var clearDriversButton = $('#clearDriversButton');
    var outputDriversForm = $('#outputDriversForm');
    var inputDriverForm = $('#inputDriverForm');
    var successInputDriverLabel = $('#successInputDriverLabel');
    var dangerInputDriverLabel = $('#dangerInputDriverLabel');
    var lastAddedDriverId;

    function sendDriverToAdding() {
        $.ajax({
            url: '/admin/orders/driver?add',
            type: 'post',
            dataType: 'json',
            data: {
                "orderId" : orderIdForm.val(),
                "driverId" : inputDriverForm.val()
            }
        }).done(function (response) {
            if (response.type == 'passed') {
                var selectedInputDriverForm = $('#inputDriverForm option:selected');
                outputDriversForm.val(outputDriversForm.val() + selectedInputDriverForm.text() + '\n');
                selectedInputDriverForm.prop('disabled', 'true');
                lastAddedDriverId = inputDriverForm.val();
                successInputDriverLabel.text(response.entry).show();
            } else {
                dangerInputDriverLabel.text(response.entry).show();
            }
            addDriverButton.removeAttr('disabled');
            clearDriversButton.removeAttr('disabled');
        })
    }

    function addDriverToOrder() {
        allMessageLabels.hide();
        if (inputDriverForm.val() != lastAddedDriverId) {
            sendDriverToAdding();
            addDriverButton.prop('disabled', 'true');
            clearDriversButton.prop('disabled', 'true');
        }
    }

    function sendToClearingDrivers() {
        $.ajax({
            url: '/admin/orders/driver?clear',
            type: 'post',
            dataType: 'json',
            data: {
                "orderId" : orderIdForm.val()
            }
        }).done(function (response) {
            if (response.type == 'passed') {
                outputDriversForm.val('');
                successInputDriverLabel.text(response.entry).show();
                $('#inputDriverForm option').removeAttr('disabled');
            } else {
                dangerInputDriverLabel.text(response.entry).show();
            }
            addDriverButton.removeAttr('disabled');
            clearDriversButton.removeAttr('disabled');
        })
    }

    function clearDriversOfOrder() {
        allMessageLabels.hide();
        sendToClearingDrivers();
        addDriverButton.prop('disabled', 'true');
        clearDriversButton.prop('disabled', 'true');
    }

    /* CARGO */
    var addCargoButton = $('#addCargoButton');
    var clearCargoesButton = $('#clearCargoesButton');
    var outputCargoesForm = $('#outputCargoesForm');
    var inputCargoForm = $('#inputCargoForm');
    var successInputCargoLabel = $('#successInputCargoLabel');
    var dangerInputCargoLabel = $('#dangerInputCargoLabel');
    var lastAddedCargoId;

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
                lastAddedCargoId = inputCargoForm.val();
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
        if (inputCargoForm.val() != lastAddedCargoId) {
            sendCargoToAdding();
            addCargoButton.prop('disabled', 'true');
            clearCargoesButton.prop('disabled', 'true');
        }
    }

    function sendToClearingCargoes() {
        $.ajax({
            url: '/admin/orders/cargo?clear',
            type: 'post',
            dataType: 'json',
            data: {
                "orderId" : orderIdForm.val()
            }
        }).done(function (response) {
            if (response.type == 'passed') {
                outputCargoesForm.val('');
                successInputCargoLabel.text(response.entry).show();
                $('#inputCargoForm option').removeAttr('disabled');
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
