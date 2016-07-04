<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">Cargotransportations</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="index.jsp">Home</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="userServlet?action=user_edit&user_name=${user_name}">
                <span class="glyphicon glyphicon-user"></span> Account</a></li>
            <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Sign Out</a></li>
        </ul>
    </div>
</nav>