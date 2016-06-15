<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
    <body>
        <span><a href="driverServlet?action=refresh">Refresh</a></span>
        <span><a href="driverServlet?action=add">Add</a></span>
        <table>
            <tr>
                <th>Number</th>
                <th>FirstName</th>
                <th>LastName</th>
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
                    <td><a href="driverServlet?action=edit&driver_number=${driver.number}">Edit</a></td>
                    <td><a href="driverServlet?action=delete&driver_number=${driver.number}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>