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
                <a class="btn btn-info" role="button" href="userServlet?action=refresh">Refresh</a>
                <a class="btn btn-info" role="button" href="userServlet?action=add">Add</a>
            </div>

            <table class="table table-striped">
                <tr>
                    <th>Name</th>
                    <th>Role</th>
                    <th>Creation Date</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.userRole}"/></td>
                        <td><c:out value="${user.creationDate}"/></td>
                        <td><a class="btn btn-primary"
                               href="userServlet?action=edit&user_name=${user.name}">Edit</a></td>
                        <td>
                            <form action="userServlet" method="post">
                                <input type="text" name="action" value="perform_deleting" hidden/>
                                <input type="text" name="user_name" value="${user.name}" hidden/>
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