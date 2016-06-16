<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <body>
        <form action="cargoServlet" method="post">
            <c:if test="${action eq 'add'}"><input name="action" value="perform_adding" hidden/></c:if>
            <c:if test="${action eq 'edit'}"><input name="action" value="perform_editing" hidden/></c:if>
            <table>
                <tr>
                    <td>Number:</td>
                    <td><input type="text" value="${cargo.number}" readonly
                               <c:if test="${action eq 'edit'}">name="cargo_number"</c:if>/></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" value="${cargo.name}"/></td>
                </tr>
                <tr>
                    <td>Weight:</td>
                    <td><input type="text" name="weight" value="${cargo.weight}"/></td>
                </tr>
                <tr>
                    <td>Departure City:</td>
                    <td><input type="text" value="${cargo.departureCity}"
                               <c:if test="${action eq 'edit'}">readonly</c:if>
                               <c:if test="${action ne 'edit'}">name="departure_city"</c:if>/></td>
                </tr>
                <tr>
                    <td>Arrival City:</td>
                    <td><input type="text" value="${cargo.arrivalCity}"
                               <c:if test="${action eq 'edit'}">readonly</c:if>
                               <c:if test="${action ne 'edit'}">name="arrival_city"</c:if>/></td>
                </tr>
                <tr>
                    <td>Status:</td>
                    <td><input type="text" value="${cargo.status}" readonly/></td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
            <span><a href="cargoServlet?action=refresh">Cancel</a></span>
        </form>
        <span style="color: darkgreen"><c:out value="${success_message}"/></span>
        <span style="color: darkred"><c:out value="${error_message}"/></span>
        <c:remove var="success_message" scope="session"/>
        <c:remove var="error_message" scope="session"/>
    </body>
</html>