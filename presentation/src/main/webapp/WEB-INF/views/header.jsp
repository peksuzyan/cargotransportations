<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="app_name" var="appName" />

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">${appName}</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="#">
                    <span class="glyphicon glyphicon-user"></span> Management</a>
            </li>
            <li>
                <a href="#">
                    <span class="glyphicon glyphicon-log-out"></span> Sign Out</a>
            </li>
        </ul>
    </div>
</nav>
