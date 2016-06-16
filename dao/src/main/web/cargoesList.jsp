<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <body>
        <span><a href="cargoServlet?action=refresh">Refresh</a></span>
        <span><a href="cargoServlet?action=add">Add</a></span>
        <table>
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
                    <td><a href="cargoServlet?action=edit&cargo_number=${cargo.number}">Edit</a></td>
                    <td>
                        <form action="cargoServlet" method="post">
                            <input type="text" name="action" value="perform_deleting" hidden/>
                            <input type="text" name="cargo_number" value=${cargo.number} hidden/>
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <span style="color: darkgreen"><c:out value="${success_message}"/></span>
        <span style="color: darkred"><c:out value="${error_message}"/></span>
        <c:remove var="success_message" scope="session"/>
        <c:remove var="error_message" scope="session"/>
    </body>
</html>