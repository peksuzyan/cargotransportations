<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="app_name" var="appName"/>
<spring:message code="app_button_account" var="appButtonAccount"/>
<spring:message code="app_button_signin" var="appButtonSignin"/>
<spring:message code="app_button_signout" var="appButtonSignout"/>

<spring:url var="account" value="/user/account"/>
<spring:url var="logout" value="/login?logout"/>
<spring:url var="home" value="/"/>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${home}">${appName}</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="${account}">
                    <span class="glyphicon glyphicon-user"></span> ${appButtonAccount}</a>
            </li>
            <li>
                <a href="${logout}">
                    <span class="glyphicon glyphicon-log-out"></span> ${appButtonSignout}</a>
            </li>
        </ul>
    </div>
</nav>
