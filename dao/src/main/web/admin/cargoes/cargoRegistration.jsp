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
                        <h3>Cargo Passport
                            <c:if test="${not empty cargo}"><kbd>#${cargo.number}</kbd></c:if>
                        </h3>
                    </div>

                </div>
                <div class="col-sm-1"></div>
            </div>

            <div class="row">
                <div class="col-sm-1"></div>
                <div class="col-sm-10">

                    <form action="cargoServlet" method="post" class="form-horizontal" role="form">

                        <c:if test="${action eq 'add'}"><input name="action" value="perform_adding" hidden/></c:if>
                        <c:if test="${action eq 'edit'}"><input name="action" value="perform_editing" hidden/></c:if>

                        <input type="hidden"
                               name="cargo_number"
                               value="${cargo.number}"
                        />

                        <div class="form-group">
                            <label class="control-label col-sm-2">Name:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       name="name"
                                       value="${cargo.name}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Weight:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       data-toggle="tooltip"
                                       data-placement="auto"
                                       title="Type digits [0-9] or dot '.'"
                                       name="weight"
                                       value="${cargo.weight}"
                                />
                            </div>
                            <label id="incorrect_weight" class="control-label text-danger"></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Departure City:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       <c:if test="${action eq 'edit'}">readonly</c:if>
                                       name="departure_city"
                                       value="${cargo.departureCity}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2">Arrival City:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       <c:if test="${action eq 'edit'}">readonly</c:if>
                                       name="arrival_city"
                                       value="${cargo.arrivalCity}"
                                />
                            </div>
                        </div>

                        <div class="form-group" <c:if test="${action eq 'add'}">hidden</c:if> >
                            <label class="control-label col-sm-2">Status:</label>
                            <div class="col-sm-4">
                                <input type="text"
                                       class="form-control"
                                       readonly
                                       name="status"
                                       value="${cargo.status}"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default"
                                        onclick="return validate_onclick()"
                                        onfocus="clear_onclick()">Save</button>
                                <a class="btn btn-default" href="cargoServlet?action=refresh">Cancel</a>
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
            var pattern = /\d+(\.\d+)?/;
            var input = document.getElementsByName('weight')[0].value;

            if (!pattern.test(input)) {
                text_error = "<code>Please, type digits [0-9] or alone dot '.'</code>";
                document.getElementById('incorrect_weight').innerHTML = text_error;
                return false;
            }
            return true;
        }
    </script>

    <script>
        function clear_onclick() {
            document.getElementById('incorrect_weight').innerHTML = '';
        }
    </script>

    <script>
        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>

</html>