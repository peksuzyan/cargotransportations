<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="title_cargoes" var="titleCargoes"/>
<spring:message code="cargo_number" var="cargoNumber" />
<spring:message code="cargo_name" var="cargoName" />
<spring:message code="cargo_weight" var="cargoWeight" />
<spring:message code="cargo_departure_city" var="cargoDepartureCity" />
<spring:message code="cargo_arrival_city" var="cargoArrivalCity" />
<spring:message code="cargo_status" var="cargoStatus" />
<spring:message code="app_button_edit" var="appButtonEdit" />
<spring:message code="app_button_delete" var="appButtonDelete" />
<spring:message code="app_button_create" var="appButtonCreate" />
<spring:message code="app_button_refresh" var="appButtonRefresh" />

<h2>${titleCargoes}</h2>

<c:if test="${not empty message}">
    <c:if test="${message.type eq 'success'}"><div>${message.entry}</div></c:if>
    <c:if test="${message.type eq 'error'}"><div>${message.entry}</div></c:if>
</c:if>

<h3>
    <a href="/cargoes?add">${appButtonCreate}</a>
    <a>|</a>
    <a href="/cargoes">${appButtonRefresh}</a>
</h3>

<c:if test="${not empty cargoes}">
    <table>
        <thead>
            <tr>
                <th>${cargoNumber}</th>
                <th>${cargoName}</th>
                <th>${cargoWeight}</th>
                <th>${cargoDepartureCity}</th>
                <th>${cargoArrivalCity}</th>
                <th>${cargoStatus}</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cargo" items="${cargoes}">
                <tr>
                    <td>${cargo.number}</td>
                    <td>${cargo.name}</td>
                    <td>${cargo.weight}</td>
                    <td>${cargo.departureCity}</td>
                    <td>${cargo.arrivalCity}</td>
                    <td>${cargo.status}</td>
                    <td><a href="/cargoes/${cargo.number}">${appButtonEdit}</a></td>
                    <td><a href="/cargoes/${cargo.number}?delete">${appButtonDelete}</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>
