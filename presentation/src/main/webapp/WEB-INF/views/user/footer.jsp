<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="app_languages" var="appLanguage"/>
<spring:message code="app_button_en" var="appButtonEn"/>
<spring:message code="app_button_ru" var="appButtonRu"/>

<c:set var="languageClass" value="list-group-item" />

<div class="list-group">
    <span class="${languageClass} list-group-item-warning">${appLanguage}</span>
    <a id="en" class="${languageClass}">${appButtonEn}</a>
    <a id="ru" class="${languageClass}">${appButtonRu}</a>
</div>

<script>
    $(document).ready(function(){
       var path = $(location).attr('pathname');
       $('#en').attr('href', path + '?lang=en');
       $('#ru').attr('href', path + '?lang=ru');
    });
</script>