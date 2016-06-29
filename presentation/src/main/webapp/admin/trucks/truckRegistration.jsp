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

                    <form action="truckServlet" method="post" class="form-horizontal" role="form">

                        <c:if test="${action eq 'add'}"><input name="action" value="perform_adding" hidden /></c:if>
                        <c:if test="${action eq 'edit'}"><input name="action" value="perform_editing" hidden /></c:if>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Number:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="truck_number" value="${truck.number}"
                                       <c:if test="${action eq 'edit'}">readonly</c:if> />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">People:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="people" value="${truck.people}" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Active:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="active" value="${truck.active}" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Capacity:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="capacity" value="${truck.capacity}" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">City:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" value="${truck.city}"
                                       <c:if test="${action eq 'add'}">name="city"</c:if>
                                       <c:if test="${action ne 'add'}">readonly</c:if> />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">Save</button>
                                <a class="btn btn-default" href="truckServlet?action=refresh">Cancel</a>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="col-sm-1"></div>
            </div>
        </div>
    </body>
</html>