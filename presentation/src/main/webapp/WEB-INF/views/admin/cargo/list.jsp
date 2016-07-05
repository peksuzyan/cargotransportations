<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Cargoes</title>
</head>
    <body>
    Hello World!
        <c:if test="${not empty cargoes}">
            <table>
                <c:forEach var="cargo" items="${cargoes}">
                    <tr>
                        <td>${cargo.number}</td>
                        <td>${cargo.name}</td>
                        <td>${cargo.weight}</td>
                        <td>${cargo.departureCity}</td>
                        <td>${cargo.arrivalCity}</td>
                        <td>${cargo.status}</td>
                        <td><a href="/cargo/${cargo.number}?edit">Edit</a></td>
                        <td><a href="/cargo/${cargo.number}?delete">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
