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
                <h3>User Passport
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

                <c:if test="${action eq 'add'}">
                    <input name="action" value="perform_adding" hidden/></c:if>
                <c:if test="${action eq 'edit'}">
                    <input name="action" value="perform_editing" hidden/></c:if>
                <c:if test="${action eq 'user_edit'}">
                    <input name="action" value="perform_user_editing" hidden/></c:if>

                <div class="form-group">
                    <label class="control-label col-sm-2">Name:</label>
                    <div class="col-sm-4">
                        <input type="text"
                               class="form-control"
                               name="user_name"
                               value="${user.name}"
                               <c:if test="${action ne 'add'}">readonly</c:if>
                        />
                    </div>
                    <label id="incorrect_name" class="control-label text-danger"></label>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Password:</label>
                    <div class="col-sm-4">
                        <input type="password"
                               class="form-control"
                               name="user_password"
                               value="${user.password}"
                        />
                    </div>
                    <label id="incorrect_password" class="control-label text-danger"></label>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Role:</label>
                    <div class="col-sm-4">
                        <select class="form-control"
                                name="user_role"
                                <c:if test="${action eq 'user_edit'}">readonly</c:if> >
                            <option value="ADMIN"
                                    <c:if test="${user.userRole eq 'ADMIN'}">selected</c:if> > Admin</option>
                            <option value="USER"
                                    <c:if test="${user.userRole eq 'USER'}">selected</c:if>> User</option>
                        </select>
                    </div>
                    <label id="incorrect_role" class="control-label text-danger"></label>
                </div>

                <div class="form-group" <c:if test="${action eq 'add'}">hidden</c:if> >
                    <label class="control-label col-sm-2">Creation Date:</label>
                    <div class="col-sm-4">
                        <input type="text"
                               class="form-control"
                               name="creation_date"
                               value="${user.creationDate}"
                               readonly
                        />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default"
                                onclick="return validate_on_empty()"
                                onfocus="clear_onclick()">Save</button>
                        <a class="btn btn-default" href="index.jsp">Cancel</a>
                    </div>
                </div>

            </form>
        </div>
        <div class="col-sm-1"></div>
    </div>
</div>
</body>

<script>
    function validate_on_empty() {
        var text_error  = "<code>Field cannot be empty!</code>";
        var name = document.getElementsByName('user_name')[0].value;
        var password = document.getElementsByName('user_password')[0].value;
        var role = document.getElementsByName('user_role')[0].value;
        if (name == '') {
            document.getElementById('incorrect_name').innerHTML = text_error;
            return false;
        } else if (password == '') {
            document.getElementById('incorrect_password').innerHTML = text_error;
            return false;
        } else if (role == '') {
            document.getElementById('incorrect_role').innerHTML = text_error;
            return false;
        }
        return true;
    }
</script>

<script>
    function clear_onclick() {
        document.getElementById('incorrect_name').innerHTML = '';
        document.getElementById('incorrect_password').innerHTML = '';
        document.getElementById('incorrect_role').innerHTML = '';
    }
</script>

</html>
