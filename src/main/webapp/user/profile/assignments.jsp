<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<html>
<body>
<div class="container">

    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">

            <jsp:include page="../navbar.jsp"/>
            <jsp:include page="../message.jsp"/>

            <div class="page-header">
                <h3>User Assignments
                    <c:if test="${not empty user}"><kbd>${user.name}</kbd></c:if>
                </h3>
            </div>

        </div>
        <div class="col-sm-1"></div>
    </div>

    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">

            <form action="userServlet" method="post" class="form-horizontal" role="form">

                <c:forEach items="${drivers}" var="driver">
                    <div class="form-group">
                        <label class="control-label col-sm-2">Driver:</label>
                        <div class="col-sm-4">
                            <input type="text"
                                   class="form-control"
                                   readonly
                                   value="${driver.number}"
                            />
                        </div>
                    </div>
                </c:forEach>

                <div class="form-group">
                    <label class="control-label col-sm-2">Truck:</label>
                    <div class="col-sm-4">
                        <input type="text"
                               class="form-control"
                               readonly
                               value="${truck_number}"
                        />
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Order:</label>
                    <div class="col-sm-4">
                        <input type="text"
                               class="form-control"
                               readonly
                               value="${order_number}"
                        />
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Route Points:</label>
                    <div class="col-sm-4">
                        <input type="text"
                               class="form-control"
                               readonly
                               value="${suitable_routes}"
                        />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="index.jsp">OK</a>
                    </div>
                </div>

            </form>
        </div>
        <div class="col-sm-1"></div>
    </div>
</div>
</body>

</html>
