<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<spring:message code="title_account" var="title"/>
<spring:message code="title_assignment" var="titleAssignment"/>
<spring:message code="title_passport_user" var="titleUserPassport" />
<spring:message code="account_user_not_driver" var="accountUserNotDriver" />
<spring:message code="account_user_not_order" var="accountUserNotOrder" />
<spring:message code="user_id" var="userId" />
<spring:message code="user_email" var="userEmail" />
<spring:message code="user_password" var="userPassword" />
<spring:message code="user_role" var="userRole" />
<spring:message code="user_creation_date" var="userCreationDate" />
<spring:message code="account_driver_id" var="accountDriverId" />
<spring:message code="account_co-drivers_id" var="accountCoDriversId" />
<spring:message code="account_truck_number" var="accountTruckNumber" />
<spring:message code="account_order_id" var="accountOrderId" />
<spring:message code="account_route_cities" var="accountRouteCities" />
<spring:message code="app_button_change_password" var="appButtonChangePassword" />
<spring:message code="app_button_refresh" var="appButtonRefresh" />
<spring:message code="app_button_save" var="appButtonSave"/>
<spring:message code="app_button_cancel" var="appButtonCancel"/>

<spring:url var="accountURL" value="/user/account"/>

<c:set var="id" value="id"/>
<c:set var="email" value="email"/>
<c:set var="password" value="password"/>
<c:set var="role" value="userRole" />
<c:set var="cdate" value="creationDate" />

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

<c:if test="${message.type eq 'success'}">
    <div class="col-xs-12">
        <div class="alert alert-success"><strong>${message.entry}</strong></div>
    </div>
</c:if>

<div class="col-xs-12">
    <div class="panel panel-default">
        <div class="panel-heading">${title}</div>
        <div class="panel-body">
            <div class="btn-group">
                <a class="btn btn-info" role="button"
                   data-toggle="modal" data-target="#changing">${appButtonChangePassword}</a>
                <a class="btn btn-info" role="button" href="${accountURL}">${appButtonRefresh}</a>
            </div>
        </div>
    </div>
</div>

<div class="col-xs-12">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="btn-group">

                <c:if test="${empty driver}">
                    <p>${accountUserNotDriver}</p>
                </c:if>

                <c:if test="${not empty driver && empty driver.truck}">
                    <p>${accountUserNotOrder}</p>
                </c:if>

                <c:if test="${not empty driver && not empty driver.truck}">
                    <form class="${formClass}">

                        <div class="${outerDivClass}">
                            <label class="${headerClass}">
                                <h3>${titleAssignment}</h3>
                            </label>
                        </div>

                        <div class="${outerDivClass}">
                            <label class="${labelClass}">${accountDriverId}:</label>
                            <div class="${innerDivClass}">
                                <input title="driver_id" class="${inputClass}"
                                       value="${driver.id}" readonly />
                            </div>
                        </div>

                        <div class="${outerDivClass}">
                            <label class="${labelClass}">${accountTruckNumber}:</label>
                            <div class="${innerDivClass}">
                                <input title="truck_number" class="${inputClass}"
                                       value="${driver.truck.number}" readonly />
                            </div>
                        </div>

                        <c:if test="${not empty order}">
                            <div class="${outerDivClass}">
                                <label class="${labelClass}">${accountOrderId}:</label>
                                <div class="${innerDivClass}">
                                    <input title="order_id" class="${inputClass}"
                                           value="${order.id}" readonly />
                                </div>
                            </div>

                            <c:forEach items="${order.drivers}" var="codriver">
                                <c:if test="${codriver.id ne driver.id}">
                                    <div class="${outerDivClass}">
                                        <label class="${labelClass}">${accountCoDriversId}:</label>
                                        <div class="${innerDivClass}">
                                            <input title="co-drivers_id" class="${inputClass}"
                                                   value="${codriver.id}" readonly />
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>

                            <c:if test="${not empty order.route}">
                                <c:forEach items="${order.route.cities}" var="city">
                                    <div class="${outerDivClass}">
                                        <label class="${labelClass}">${accountRouteCities}:</label>
                                        <div class="${innerDivClass}">
                                            <input title="route_cities" class="${inputClass}"
                                                   value="${city}" readonly />
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </c:if>
                    </form>
                </c:if>

            </div>
        </div>
    </div>
</div>

<div class="col-xs-12">
    <div id="changing" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">

<c:if test="${message.type eq 'error'}">
    <div class="alert alert-danger"><strong>${message.entry}</strong></div>
</c:if>

<form:form method="post" modelAttribute="user" cssClass="${formClass}" role="form" id="userForm">

    <div class="${outerDivClass}">
        <label class="${headerClass}">
            <h3>${titleUserPassport} <kbd>#${user.id}</kbd></h3>
        </label>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${id}" cssClass="${labelClass}">${userId}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${id}" cssClass="${inputClass}"
                        value="${user.id}" readonly="true" />
        </div>
        <div><form:errors path="${id}" cssClass="${errorsClass}" /></div>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${email}" cssClass="${labelClass}">${userEmail}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${email}" cssClass="${inputClass}"
                        value="${user.email}" name="${email}" readonly="true"/>
        </div>
        <form:errors path="${email}" cssClass="${errorsClass}" for="${email}"/>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${password}" cssClass="${labelClass}">${userPassword}:</form:label>
        <div class="${innerDivClass}">
            <form:password path="${password}" cssClass="${inputClass}"
                           value="${user.password}" name="${password}"/>
        </div>
        <form:errors path="${password}" cssClass="${errorsClass}" for="${password}"/>
    </div>

    <div class="${outerDivClass}" hidden>
        <form:label path="${role}" cssClass="${labelClass}">${userRole}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${role}" cssClass="${inputClass}"
                        value="${user.userRole}" name="${role}"/>
        </div>
        <div><form:errors path="${role}" cssClass="${errorsClass}" for="${role}" /></div>
    </div>

    <div class="${outerDivClass}" hidden>
        <form:label path="${cdate}" cssClass="${labelClass}">${userCreationDate}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${cdate}" cssClass="${inputClass}"
                        value="${user.creationDate}" name="${cdate}"/>
        </div>
        <div><form:errors path="${cdate}" cssClass="${errorsClass}" /></div>
    </div>

    <div class="${outerDivClass}">
        <div class="${buttonDivClass}">
            <button class="${buttonClass}" type="submit">${appButtonSave}</button>
            <button class="${buttonClass}" data-dismiss="modal">${appButtonCancel}</button>
        </div>
    </div>

</form:form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>