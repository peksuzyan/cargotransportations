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
                        <a class="btn btn-info" role="button" href="driverServlet?action=refresh">Refresh</a>
                        <a class="btn btn-info" role="button" href="driverServlet?action=add">Add</a>
                    </div>

                    <table class="table table-striped">
                        <tr>
                            <th>Number</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Hours</th>
                            <th>Status</th>
                            <th>City</th>
                            <th>Truck</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="driver" items="${drivers}">
                            <tr>
                                <td><c:out value="${driver.number}" /></td>
                                <td><c:out value="${driver.firstName}" /></td>
                                <td><c:out value="${driver.lastName}" /></td>
                                <td><c:out value="${driver.hours}" /></td>
                                <td><c:out value="${driver.status}" /></td>
                                <td><c:out value="${driver.city}" /></td>
                                <td><c:out value="${driver.truck}" /></td>
                                <td>
                                    <a class="btn btn-primary" role="button"
                                       href="driverServlet?action=edit&driver_number=${driver.number}">Edit</a>
                                </td>
                                <td>
                                    <form action="driverServlet" method="post">
                                        <input type="text" name="action" value="perform_deleting" hidden />
                                        <input type="text" name="driver_number" value=${driver.number} hidden />
                                        <input class="btn btn-primary" role="button" type="submit" value="Delete">
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