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

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2">Number:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" value="${order.number}" readonly />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Status:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" value="${order.status}" readonly />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Creation Date:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" value="${order.creationDate}" readonly />
                            </div>
                        </div>
                    </form>

                    <form action="orderServlet" method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2">Truck:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" value="${order.truck}" readonly />
                                <input type="text" name="order_number" value="${order.number}" hidden/>
                                <input type="text" name="action" value="perform_truck_refusing" hidden />
                            </div>
                            <div>
                                <input class="btn btn-default" type="submit" value="Refuse"/>
                            </div>
                        </div>
                    </form>

                    <form action="orderServlet" method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2"></label>
                            <div class="col-sm-4">
                                <select class="form-control" name="truck_number">
                                    <c:forEach var="truck" items="${suitable_trucks}">
                                        <option value="${truck.number}">${truck.number}</option>
                                    </c:forEach>
                                </select>
                                <input type="text" name="order_number" value="${order.number}" hidden/>
                                <input type="text" name="action" value="perform_truck_assigning" hidden/>
                            </div>
                            <div>
                                <input class="btn btn-default" type="submit" value="Assign"/>
                            </div>
                        </div>
                    </form>

                    <form action="orderServlet" method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2">Route:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" value="${order.route}" readonly />
                                <input type="text" name="order_number" value="${order.number}" hidden/>
                                <input type="text" name="action" value="perform_route_refusing" hidden />
                            </div>
                            <div>
                                <input class="btn btn-default" type="submit" value="Refuse"/>
                            </div>
                        </div>
                    </form>

                    <form action="orderServlet" method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2"></label>
                            <div class="col-sm-4">
                                <select class="form-control" name="route_number">
                                    <c:forEach var="route" items="${routes}">
                                        <option value="${route}">${route}</option>
                                    </c:forEach>
                                </select>
                                <input type="text" name="order_number" value="${order.number}" hidden/>
                                <input type="text" name="action" value="perform_route_assigning" hidden/>
                            </div>
                            <div>
                                <input class="btn btn-default" type="submit" value="Assign"/>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2">Assigned Cargoes:</label>
                        </div>
                    </form>

                    <form action="orderServlet" method="post" class="form-horizontal" role="form">
                        <div class="col-sm-6 form-group">
                            <table class="table table-striped">
                                <tr>
                                    <th>Number</th>
                                    <th>Name</th>
                                    <th>Weight</th>
                                    <th></th>
                                </tr>
                                <c:forEach var="cargo" items="${order.cargoes}">
                                    <tr>
                                        <td><c:out value="${cargo.number}" /></td>
                                        <td><c:out value="${cargo.name}" /></td>
                                        <td><c:out value="${cargo.weight}" /></td>
                                        <td>
                                            <form action="orderServlet" method="post">
                                                <input type="text" name="action" value="perform_cargo_excluding" hidden />
                                                <input type="text" name="order_number" value="${order.number}" hidden />
                                                <input type="text" name="cargo_number" value="${cargo.number}" hidden />
                                                <input type="submit" value="Delete">
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </form>

                    <form action="orderServlet" method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-4">
                                <select class="form-control" name="cargo_number">
                                    <c:forEach var="cargo" items="${suitable_cargoes}">
                                        <option value="${cargo.number}">${cargo.number} ${cargo.name}</option>
                                    </c:forEach>
                                </select>
                                <input type="text" name="order_number" value="${order.number}" hidden/>
                                <input type="text" name="action" value="perform_cargo_adding" hidden/>
                            </div>
                            <div>
                                <input class="btn btn-default" type="submit" value="Add cargo"/>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="control-label col-sm-2">Assigned Drivers:</label>
                        </div>
                    </form>

                    <form action="orderServlet" method="post" class="form-horizontal" role="form">
                        <div class="col-sm-6 form-group">
                            <table class="table table-striped">
                                <tr>
                                    <th>Number</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th></th>
                                </tr>
                                <c:forEach var="driver" items="${order.drivers}">
                                    <tr>
                                        <td><c:out value="${driver.number}" /></td>
                                        <td><c:out value="${driver.firstName}" /></td>
                                        <td><c:out value="${driver.lastName}" /></td>
                                        <td>
                                            <form action="orderServlet" method="post">
                                                <input type="text" name="action" value="perform_driver_excluding" hidden />
                                                <input type="text" name="driver_number" value="${driver.number}" hidden />
                                                <input class="btn btn-default" type="submit" value="Delete">
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4">
                                <select class="form-control" name="driver_number">
                                    <c:forEach var="driver" items="${suitable_drivers}">
                                        <option value="${driver.number}">${driver.number}</option>
                                    </c:forEach>
                                </select>
                                <input type="text"
                                       name="order_number"
                                       value="${driver.number} ${driver.lastName}"
                                       hidden/>
                                <input type="text" name="action" value="perform_driver_adding" hidden/>
                            </div>
                            <div>
                                <input class="btn btn-default" type="submit" value="Add driver"/>
                            </div>
                        </div>
                    </form>

                    <div class="col-sm-4"></div>

                    <form action="orderServlet" method="post" class="form-horizontal" role="form">
                        <div class="form-group" class="col-sm-offset-2">
                            <a class="btn btn-default" href="orderServlet?action=refresh">OK</a>
                            <input type="text" name="order_number" value="${order.number}" hidden/>
                            <input type="text" name="action" value="perform_order_processing" hidden/>
                            <input class="btn btn-default" type="submit" value="Send to performing"/>
                        </div>
                    </form>

                </div>
                <div class="col-sm-1"></div>
            </div>
        </div>
    </body>
</html>