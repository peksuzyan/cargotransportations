<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
    <body>
        <form action="driverServlet" method="post">
            <c:if test="${action eq 'add'}"><input name="action" value="perform_adding" hidden /></c:if>
            <c:if test="${action eq 'edit'}"><input name="action" value="perform_editing" hidden /></c:if>
            <table>
                <tr>
                    <td>Number:</td>
                    <td><input type="text" value="${driver.number}" readonly
                               <c:if test="${action eq 'edit'}">name="driver_number"</c:if>/> </td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="first_name" value="${driver.firstName}" /> </td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input type="text" name="last_name" value="${driver.lastName}" /> </td>
                </tr>
                <tr>
                    <td>Hours:</td>
                    <td><input type="text" value="${driver.hours}" readonly /> </td>
                </tr>
                <tr>
                    <td>Status:</td>
                    <td><input type="text" value="${driver.status}" readonly /> </td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" value="${driver.city}"
                               <c:if test="${action eq 'add'}">name="city"</c:if>
                               <c:if test="${action ne 'add'}">readonly</c:if> /> </td>
                </tr>
                <tr>
                    <td>Truck:</td>
                    <td><input type="text" value="${driver.truck}" readonly /> </td>
                </tr>
            </table>
            <input type="submit" value="Save"/>
            <span><a href="driverServlet?action=refresh">Cancel</a></span>
        </form>
        <span style="color: darkgreen"><c:out value="${success_message}" /></span>
        <span style="color: darkred"><c:out value="${error_message}" /></span>
        <c:remove var="success_message" scope="session" />
        <c:remove var="error_message" scope="session" />
    </body>
</html>