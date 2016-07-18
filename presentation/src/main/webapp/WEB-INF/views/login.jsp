<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="user_email" var="userEmail"/>
<spring:message code="user_password" var="userPassword"/>
<spring:message code="placeholder_email" var="placeholderEmail"/>
<spring:message code="placeholder_password" var="placeholderPassword"/>
<spring:message code="app_button_signin" var="appButtonSignin"/>

<c:if test="${not empty message}">
    <c:if test="${message.type eq 'error'}">
        <div class="alert alert-danger"><strong>${message.entry}</strong></div>
    </c:if>
    <c:if test="${message.type eq 'success'}">
        <div class="alert alert-success"><strong>${message.entry}</strong></div>
    </c:if>
</c:if>

<form action="<c:url value='/j_spring_security_check'/>" method="post" class="form-horizontal" role="form">

    <div class="form-group">
        <label class="control-label col-xs-4">${userEmail}:</label>
        <div class="col-xs-4">
            <input type="text" class="form-control"
                   placeholder="${placeholderEmail}" name="username"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-xs-4">${userPassword}:</label>
        <div class="col-xs-4">
            <input type="password" class="form-control"
                   placeholder="${placeholderPassword}" name="password"/>
        </div>
    </div>

    <%--<div class="form-group">
        <label class="control-label col-xs-4">Remember me:</label>
        <div class="col-xs-4">
            <input type="checkbox" class="form-control" name="_spring_security_remember_me"/>
        </div>
    </div>--%>

    <div class="form-group">
        <div class="col-xs-offset-4 col-xs-2">
            <button type="submit" class="btn btn-default">${appButtonSignin}</button>
        </div>
    </div>

</form>