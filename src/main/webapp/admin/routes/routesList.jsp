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

                    <div class="btn-group">
                        <a class="btn btn-info" role="button" href="routeServlet?action=refresh">Refresh</a>
                        <a class="btn btn-info" role="button" href="routeServlet?action=add">Add</a>
                    </div>

                <table class="table table-striped">
                    <tr>
                        <th>Number</th>
                        <th>Duration</th>
                        <th>Route Points</th>
                        <th></th>
                    </tr>
                    <c:forEach var="route" items="${routes}">
                        <tr>
                            <td><c:out value="${route.number}" /></td>
                            <td><c:out value="${route.duration}" /></td>
                            <td><c:out value="${route.cities}" /></td>
                            <td>
                                <form action="routeServlet" method="post">
                                    <input type="text" name="action" value="perform_deleting" hidden />
                                    <input type="text" name="route_number" value=${route.number} hidden />
                                    <input class="btn btn-primary" type="submit" value="Delete">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </div>
                <div class="col-sm-1"></div>
            </div>
        </div>
    </body>
</html>