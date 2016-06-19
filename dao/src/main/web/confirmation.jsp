<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
    <body>
        <span style="color: darkgreen"><c:out value="${success_message}" /></span>
        <span style="color: darkred"><c:out value="${error_message}" /></span>
        <br />
        <span><a href="driverServlet?action=refresh">OK</a></span>
        <c:remove var="success_message" scope="session" />
        <c:remove var="error_message" scope="session" />
    </body>
</html>
