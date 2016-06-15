<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
    <body>
        <form action="truckServlet" method="post">
            <c:if test="${action eq 'add'}"><input name="action" value="perform_adding" hidden /></c:if>
            <c:if test="${action eq 'edit'}"><input name="action" value="perform_editing" hidden /></c:if>
            <table>
                <tr>
                    <td>Number:</td>
                    <td><input type="text" name="truck_number" value="${truck.number}"
                               <c:if test="${action eq 'edit'}">readonly</c:if>/> </td>
                </tr>
                <tr>
                    <td>People:</td>
                    <td><input type="text" name="people" value="${truck.people}" /> </td>
                </tr>
                <tr>
                    <td>Active:</td>
                    <td><input type="text" name="active" value="${truck.active}" /> </td>
                </tr>
                <tr>
                    <td>Capacity:</td>
                    <td><input type="text" name="capacity" value="${truck.capacity}"  /> </td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" value="${truck.city}"
                               <c:if test="${action eq 'add'}">name="city"</c:if>
                               <c:if test="${action ne 'add'}">readonly</c:if> /> </td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
            <span><a href="truckServlet?action=refresh">Cancel</a></span>
        </form>
        <span style="color: darkgreen"><c:out value="${success_message}" /></span>
        <span style="color: darkred"><c:out value="${error_message}" /></span>
        <c:remove var="success_message" scope="session" />
        <c:remove var="error_message" scope="session" />
    </body>
</html>