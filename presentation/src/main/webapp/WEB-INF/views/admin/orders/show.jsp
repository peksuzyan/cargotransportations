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
<spring:message code="app_button_save" var="appButtonSave"/>
<spring:message code="app_button_cancel" var="appButtonCancel"/>
<spring:message code="app_button_delete" var="appButtonDelete"/>
<spring:message code="confirm_order_deleting_text" var="confirmOrderDeletingText"/>

<c:set var="id" value="id"/>
<c:set var="status" value="status"/>
<c:set var="cargoes" value="cargoes"/>
<c:set var="truck" value="truck.number"/>
<c:set var="drivers" value="drivers"/>
<c:set var="route" value="route.id"/>
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

<spring:url var="ordersURL" value="/admin/orders"/>
<spring:url var="checkURL" value="${ordersURL}/${order.id}?check"/>
<spring:url var="deleteURL" value="${ordersURL}/${order.id}?delete"/>
<spring:url var="cancelURL" value="${ordersURL}"/>

<c:if test="${message.type eq 'error'}">
    <div class="alert alert-danger"><strong>${message.entry}</strong></div>
</c:if>

<form:form method="post" modelAttribute="order" cssClass="${formClass}" role="form" id="truckForm">

    <div class="${outerDivClass}">
        <label class="${headerClass}">
            <h3>${titleOrderPassport}
                <c:if test="${order.id != 0}"> <kbd id="orderTitle">#${order.id}</kbd></c:if>
            </h3>
        </label>
    </div>

    <c:if test="${order.id != 0}">
        <div class="${outerDivClass}">
            <form:label path="${id}" cssClass="${labelClass}">${orderId}:</form:label>
            <div class="${innerDivClass}">
                <form:input path="${id}" cssClass="${inputClass}" value="${order.id}" readonly="true"/>
            </div>
            <div><form:errors path="${id}" cssClass="${errorsClass}"/></div>
        </div>
    </c:if>

</form:form>

<c:if test="${order.id != 0}">
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
    /*$('#truckForm').validate();*/
</script>
