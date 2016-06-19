<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Cargotransportations</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="login.jsp">Home</a></li>
            <li><a href="driverServlet?action=refresh">Drivers</a></li>
            <li><a href="truckServlet?action=refresh">Trucks</a></li>
            <li><a href="cargoServlet?action=refresh">Cargoes</a></li>
            <li><a href="orderServlet?action=refresh">Orders</a></li>
            <li><a href="routeServlet?action=refresh">Routes</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>