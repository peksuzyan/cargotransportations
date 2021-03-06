<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="app_name" var="appName" />
<spring:message code="app_button_account" var="appButtonAccount" />
<spring:message code="app_button_signin" var="appButtonSignin" />
<spring:message code="app_button_signout" var="appButtonSignout" />

<spring:url var="login" value="/login" />

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${login}">${appName}</a>
        </div>
        <%--<ul class="nav navbar-nav navbar-right">
            <li>
                <a href="${login}">
                    <span class="glyphicon glyphicon-log-in"></span> ${appButtonSignin}</a>
            </li>
        </ul>--%>
    </div>
</nav>
