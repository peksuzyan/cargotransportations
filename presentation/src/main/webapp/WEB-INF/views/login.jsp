<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="user_email" var="userEmail" />
<spring:message code="user_password" var="userPassword" />
<spring:message code="placeholder_email" var="placeholderEmail" />
<spring:message code="placeholder_password" var="placeholderPassword" />
<spring:message code="app_button_signin" var="appButtonSignin" />

<form action="loginServlet" method="post" class="form-horizontal" role="form">
    
    <div class="form-group">
        <label class="control-label col-xs-4">${userEmail}:</label>
        <div class="col-xs-4">
            <input type="text" class="form-control"
                   placeholder="${placeholderEmail}" name="user_name" />
        </div>
    </div>
    
    <div class="form-group">
        <label class="control-label col-xs-4">${userPassword}:</label>
        <div class="col-xs-4">
            <input type="password" class="form-control"
                   placeholder="${placeholderPassword}" name="user_password" />
        </div>
    </div>
    
    <div class="form-group">
        <div class="col-xs-offset-4 col-xs-2">
            <button type="submit" class="btn btn-default">${appButtonSignin}</button>
        </div>
    </div>
    
</form>