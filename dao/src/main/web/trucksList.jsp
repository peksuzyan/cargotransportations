<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
    <body>
        <span><a href="truckServlet?action=refresh">Refresh</a></span>
        <span><a href="truckServlet?action=add">Add</a></span>
        <table>
            <tr>
                <th>Number</th>
                <th>People</th>
                <th>Active</th>
                <th>Capacity</th>
                <th>City</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="truck" items="${trucks}">
                <tr>
                    <td><c:out value="${truck.number}" /></td>
                    <td><c:out value="${truck.people}" /></td>
                    <td><c:out value="${truck.active}" /></td>
                    <td><c:out value="${truck.capacity}" /></td>
                    <td><c:out value="${truck.city}" /></td>
                    <td><a href="truckServlet?action=edit&truck_number=${truck.number}">Edit</a></td>
                    <td>
                        <form action="truckServlet" method="post">
                            <input type="text" name="action" value="perform_deleting" hidden />
                            <input type="text" name="truck_number" value=${truck.number} hidden />
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <span style="color: darkgreen"><c:out value="${success_message}" /></span>
        <span style="color: darkred"><c:out value="${error_message}" /></span>
        <c:remove var="success_message" scope="session" />
        <c:remove var="error_message" scope="session" />
    </body>
</html>