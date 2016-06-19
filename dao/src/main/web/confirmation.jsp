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

                    <a class="btn btn-info" role="button" href="#">Ok</a>

                </div>
                <div class="col-sm-1"></div>
            </div>
        </div>

    </body>
</html>
