<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

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

                    <jsp:include page="../navbar.jsp"/>
                    <jsp:include page="../message.jsp"/>

                    <form action="routeServlet" method="post" class="form-horizontal" role="form">

                        <input name="action" value="perform_adding" hidden/>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Number:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       value="${route.number}"
                                       readonly />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Duration:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       name="duration" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Route Point:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       name="city1" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Route Point:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       name="city2" />
                            </div>
                        </div>

                        <input type="text" name="cities_count" value="2" hidden/>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Save</button>
                                <a class="btn btn-default" href="routeServlet?action=refresh">Cancel</a>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="col-sm-1"></div>
            </div>
        </div>
    </body>
</html>