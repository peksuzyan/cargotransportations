<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
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

            <div class="col-sm-offset-2 col-sm-8">
                <div align="center" class="form-group">
                    <form action="logoutServlet" method="post">
                        <p>Are you sure want to sign out now?</p>
                        <input class="btn btn-default" type="submit" value="Yes, I'm sure" />
                    </form>
                </div>
            </div>

        </div>
        <div class="col-sm-1"></div>
    </div>
</div>
</body>
</html>
