<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
</head>

<html>
<body>
<div class="container">

    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">

            <jsp:include page="navbar.jsp"/>
            <jsp:include page="message.jsp"/>

            <div class="page-header">
                <h3 align="center">Welcome to International Cargotransportation Company!</h3>
            </div>

        </div>
        <div class="col-sm-1"></div>
    </div>

    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">

            <div class="col-sm-offset-4 col-lg-4">
                <p>You have passed an authentication!</p>
            </div>

        </div>
        <div class="col-sm-1"></div>
    </div>
</div>
</body>
</html>