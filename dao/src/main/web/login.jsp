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
                        <h3 align="center">International Cargotransportation Company</h3>
                    </div>

                </div>
                <div class="col-sm-1"></div>
            </div>

            <div class="row">
                <div class="col-sm-1"></div>
                <div class="col-sm-10">

                    <form action="LoginServlet" method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-4">Login:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       placeholder="Enter your username here...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">Password:</label>
                            <div class="col-sm-4">
                                <input type="password"
                                       class="form-control"
                                       placeholder="Enter your password here...">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-2">
                                <button type="submit" class="btn btn-default">Sign in</button>
                            </div>
                        </div>
                    </form>

                </div>
                <div class="col-sm-1"></div>
            </div>
        </div>
    </body>
</html>