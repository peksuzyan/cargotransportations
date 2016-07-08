<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="confirm_cargo_deleting_text" var="confirmCargoDeletingText" />
<spring:message code="app_button_delete" var="appButtonDelete" />
<spring:message code="app_button_cancel" var="appButtonCancel" />

<h3>${confirmCargoDeletingText}</h3>

<form method="post">
    <button type="submit">${appButtonDelete}</button>
    <button><a href="/cargoes">${appButtonCancel}</a></button>
</form>