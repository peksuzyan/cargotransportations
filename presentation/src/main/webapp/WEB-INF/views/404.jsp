<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:url var="pictureURL" value="images/black_hole.jpg"/>

<div class="panel panel-default">
    <div class="panel-body">
        <img src="${pictureURL}" alt="Oooh!">
    </div>
</div>