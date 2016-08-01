<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="title_passport_user" var="titleUserPassport" />
<spring:message code="user_id" var="userId" />
<spring:message code="user_email" var="userEmail" />
<spring:message code="user_password" var="userPassword" />
<spring:message code="user_role" var="userRole" />
<spring:message code="user_creation_date" var="userCreationDate" />
<spring:message code="app_button_save" var="appButtonSave" />
<spring:message code="app_button_cancel" var="appButtonCancel" />
<spring:message code="app_button_delete" var="appButtonDelete" />
<spring:message code="placeholder_email" var="placeholderEmail"/>
<spring:message code="confirm_user_deleting_text" var="confirmUserDeletingText" />

<c:set var="id" value="id" />
<c:set var="email" value="email" />
<c:set var="password" value="password" />
<c:set var="role" value="userRole" />
<c:set var="cdate" value="creationDate" />

<c:set var="formClass" value="form-horizontal" />
<c:set var="outerDivClass" value="row form-group" />
<c:set var="inputClass" value="form-control" />
<c:set var="errorsClass" value="control-label text-danger" />
<c:set var="buttonClass" value="btn btn-default" />
<c:set var="buttonWideClass" value="btn btn-default btn-block" />
<c:set var="userCheckingButtons" value="user-checking-buttons" />
<c:set var="userCheckingResult" value="user-checking-result" />

<c:set var="headerClass" value="col-lg-offset-3 col-lg-6
                                col-md-offset-3 col-md-6
                                col-sm-offset-2 col-sm-8
                                col-xs-offset-2 col-sm-8"/>
<c:set var="labelClass" value="control-label
                               col-lg-offset-1 col-lg-3
                               col-md-4
                               col-sm-4
                               col-xs-12"/>
<c:set var="innerDivClass" value="col-lg-4
                                  col-md-4
                                  col-sm-4
                                  col-xs-12"/>
<c:set var="buttonDivClass" value="col-xs-offset-4 col-xs-5"/>

<spring:url var="usersURL" value="/admin/users"/>
<spring:url var="checkURL" value="${usersURL}/${user.id}?check"  />
<spring:url var="deleteURL" value="${usersURL}/${user.id}?delete" />
<spring:url var="cancelURL" value="${usersURL}" />

<c:if test="${message.type eq 'error'}">
    <div class="alert alert-danger"><strong>${message.entry}</strong></div>
</c:if>

<form:form method="post" modelAttribute="user" cssClass="${formClass}" role="form" id="userForm">

    <div class="${outerDivClass}" align="center">
        <label class="${headerClass}">
            <h3>${titleUserPassport}
                <c:if test="${user.id != 0}"> <kbd>#${user.id}</kbd></c:if>
            </h3>
        </label>
    </div>

<c:if test="${user.id != 0}">
    <div class="${outerDivClass}">
        <form:label path="${id}" cssClass="${labelClass}">${userId}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${id}" cssClass="${inputClass}" value="${user.id}" readonly="true" />
        </div>
        <div><form:errors path="${id}" cssClass="${errorsClass}" /></div>
    </div>
</c:if>

    <div class="${outerDivClass}">
        <form:label path="${role}" cssClass="${labelClass}">${userRole}:</form:label>
        <div class="${innerDivClass}">
            <form:select path="${role}" cssClass="${inputClass}" name="${role}">
                <form:option value="USER">USER</form:option>
                <form:option value="ADMIN">ADMIN</form:option>
            </form:select>
        </div>
        <div><form:errors path="${role}" cssClass="${errorsClass}" for="${role}" /></div>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${email}" cssClass="${labelClass}">${userEmail}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${email}" cssClass="${inputClass}"
                        value="${user.email}" placeholder="${placeholderEmail}"
                        name="${email}" type="email" required="true"/>
        </div>
        <form:errors path="${email}" cssClass="${errorsClass}" for="${email}"/>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${password}" cssClass="${labelClass}">${userPassword}:</form:label>
        <div class="${innerDivClass}">
            <form:password path="${password}" cssClass="${inputClass}"
                           value="${user.password}" name="${password}" required="true"/>
        </div>
        <form:errors path="${password}" cssClass="${errorsClass}" for="${password}" />
    </div>

<c:if test="${user.id != 0}">
    <div class="${outerDivClass}">
        <form:label path="${cdate}" cssClass="${labelClass}">${userCreationDate}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${cdate}" cssClass="${inputClass}"
                        value="${user.creationDate}" readonly="true" required="true" />
        </div>
        <div><form:errors path="${cdate}" cssClass="${errorsClass}" /></div>
    </div>
</c:if>

    <div class="${outerDivClass}">
        <div class="${buttonDivClass}">
            <button class="${buttonClass} ${userCheckingButtons}" type="submit">${appButtonSave}</button>
            <c:if test="${user.id != 0}">
                <a class="${buttonClass} ${userCheckingButtons}" type="button"
                   data-toggle="modal" data-target="#deleting">${appButtonDelete}</a>
                <a class="${buttonClass}" type="button"
                   href="${cancelURL}">${appButtonCancel}</a>
            </c:if>
            <c:if test="${user.id == 0}">
                <button class="${buttonClass}" data-dismiss="modal">${appButtonCancel}</button>
            </c:if>
        </div>
    </div>

</form:form>

<c:if test="${user.id != 0}">
    <div id="deleting" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-body" align="center">
                    <div class="row">
                        <div class="col-lg-12">
                            <label>${confirmUserDeletingText}</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <form method="post" action="${user.id}?delete">
                                <button type="submit" class="${buttonWideClass}">${appButtonDelete}</button>
                                <button type="button" class="${buttonWideClass}"
                                        data-dismiss="modal">${appButtonCancel}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>

<script>
    $('#userForm').validate();
</script>
