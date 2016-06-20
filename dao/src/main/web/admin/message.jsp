<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<c:if test="${not empty error_message}">
    <div class="alert alert-danger">
        <strong>${error_message}</strong>
    </div>
</c:if>

<c:if test="${not empty success_message}">
    <div class="alert alert-success">
        <strong>${success_message}</strong>
    </div>
</c:if>

<c:remove scope="session" var="error_message"/>
<c:remove scope="session" var="success_message"/>
