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

                    <form action="orderServlet" method="post" class="btn-group">
                        <a role="button" class="btn btn-info"  href="orderServlet?action=refresh">Refresh</a>
                        <input type="text" name="action" value="perform_adding" hidden />
                        <input type="submit" class="btn btn-info"  value="Add" />
                    </form>

                </div>
                <div class="col-sm-1"></div>
            </div>

            <div class="row">
                <div class="col-sm-1"></div>
                <div class="col-sm-10">

                    <table class="table table-striped">
                        <tr>
                            <th>Number</th>
                            <th>Active</th>
                            <th>Creation Date</th>
                            <th>Truck</th>
                            <th>City</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td><c:out value="${order.number}" /></td>
                                <td><c:out value="${order.status}" /></td>
                                <td><c:out value="${order.creationDate}" /></td>
                                <td><c:out value="${order.truck.number}" /></td>
                                <td><c:out value="${order.truck.city}" /></td>
                                <td><a class="btn btn-primary"
                                       href="orderServlet?action=edit&order_number=${order.number}">Edit</a></td>
                                <td>
                                    <form action="orderServlet" method="post">
                                        <input type="text"
                                               name="action"
                                               value="perform_deleting"
                                               hidden />
                                        <input type="text"
                                               name="order_number"
                                               value="${order.number}"
                                               hidden />
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