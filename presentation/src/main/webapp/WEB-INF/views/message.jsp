<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<c:if test="${message.type eq 'success'}">
    <div class="alert alert-success"><strong>${message.entry}</strong></div>
</c:if>
<c:if test="${message.type eq 'error'}">
    <div class="alert alert-danger"><strong>${message.entry}</strong></div>
</c:if>