<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<spring:message code="app_name" var="appName" />

<html>
<head>
    <title>${appName}</title>
</head>
<body>
    <table>
        <tr>
            <td></td>
            <td>
                <tiles:insertAttribute name="header" ignore="true" />
            </td>
        </tr>
        <tr>
           <td>
               <tiles:insertAttribute name="menu" ignore="true" />
           </td>
           <td>
               <tiles:insertAttribute name="body" />
           </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <tiles:insertAttribute name="footer" ignore="true" />
            </td>
        </tr>
    </table>
</body>
</html>
