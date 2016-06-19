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

                    <div class="btn-group">
                        <a class="btn btn-info" role="button" href="cargoServlet?action=refresh">Refresh</a>
                        <a class="btn btn-info" role="button" href="cargoServlet?action=add">Add</a>
                    </div>

                    <table class="table table-striped">
                        <tr>
                            <th>Number</th>
                            <th>Name</th>
                            <th>Weight</th>
                            <th>Departure City</th>
                            <th>Arrival City</th>
                            <th>Status</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="cargo" items="${cargoes}">
                            <tr>
                                <td><c:out value="${cargo.number}"/></td>
                                <td><c:out value="${cargo.name}"/></td>
                                <td><c:out value="${cargo.weight}"/></td>
                                <td><c:out value="${cargo.departureCity}"/></td>
                                <td><c:out value="${cargo.arrivalCity}"/></td>
                                <td><c:out value="${cargo.status}"/></td>
                                <td><a class="btn btn-primary"
                                       href="cargoServlet?action=edit&cargo_number=${cargo.number}">Edit</a></td>
                                <td>
                                    <form action="cargoServlet" method="post">
                                        <input type="text" name="action" value="perform_deleting" hidden/>
                                        <input type="text" name="cargo_number" value=${cargo.number} hidden/>
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