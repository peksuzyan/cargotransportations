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

            <jsp:include page="navbar.jsp"/>
            <jsp:include page="message.jsp"/>

            <div class="page-header">
                <h3 align="center">Welcome to International Cargotransportation Company!</h3>
            </div>

        </div>
        <div class="col-sm-1"></div>
    </div>

    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">

            <div class="col-sm-offset-2 col-sm-8">
                <h4 align="center">You have passed an authentication
                    as <kbd>${user_name}</kbd> with role <kbd>${user_role}</kbd></h4>
                <h4 align="center">Please, type your personal number:</h4>
            </div>

        </div>
        <div class="col-sm-1"></div>
    </div>

    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">

            <form action="userServlet" method="get" role="form">
                <input name="action" value="show_assignments" hidden/>

                <div class="form-group">
                    <div class="col-sm-4">
                        <input type="text"
                               class="form-control"
                               name="driver_number"
                        />
                    </div>
                    <label id="incorrect_driver_number" class="control-label text-danger"></label>
                </div>

                <div class="form-group">
                    <div class="col-sm-4">
                        <button type="submit" class="btn btn-default"
                                onclick="return validate_on_empty()"
                                onfocus="clear_onclick()">Show assignments</button>
                    </div>
                </div>
            </form>

        </div>
        <div class="col-sm-1"></div>
    </div>

</div>
</body>

<script>
    function validate_onclick() {
        var text_error;
        var pattern = /\d+/;
        var input = document.getElementsByName('driver_number')[0].value;

        if (!pattern.test(input)) {
            text_error = "<code>Please, type digits [0-9]</code>";
            document.getElementById('incorrect_weight').innerHTML = text_error;
            return false;
        }
        return true;
    }
</script>

<script>
    function clear_onclick() {
        document.getElementById('incorrect_driver_number').innerHTML = '';
    }
</script>

</html>