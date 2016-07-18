<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<spring:message code="title_users" var="title"/>
<spring:message code="user_id" var="userId" />
<spring:message code="user_email" var="userEmail" />
<spring:message code="user_password" var="userPassword" />
<spring:message code="user_role" var="userRole" />
<spring:message code="user_creation_date" var="userCreationDate" />
<spring:message code="app_button_edit" var="appButtonEdit" />
<spring:message code="app_button_create" var="appButtonCreate" />
<spring:message code="app_button_refresh" var="appButtonRefresh" />

<spring:url var="usersURL" value="/admin/users"/>
<spring:url var="listGridURL" value="${usersURL}/listgrid"/>

<c:set var="localeCode" value="${pageContext.response.locale}" />

<c:if test="${message.type eq 'success'}">
    <div class="col-xs-12">
        <div class="alert alert-success"><strong>${message.entry}</strong></div>
    </div>
</c:if>

<div class="col-xs-12">
    <div class="panel panel-default">
        <div class="panel-heading">${title}</div>
        <div class="panel-body">
            <div class="btn-group">
                <a class="btn btn-info" role="button"
                   data-toggle="modal" data-target="#creating">${appButtonCreate}</a>
                <a class="btn btn-info" role="button" href="${usersURL}">${appButtonRefresh}</a>
            </div>
        </div>
    </div>
</div>

<div class="col-xs-12">
    <div id="creating" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <tiles:insertAttribute name="modal_admin"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="col-xs-12">
    <table id="jqGridList" class="table table-striped"></table>
    <div id="jqGridPager"></div>
</div>

<script type="text/javascript">
    $(function(){
        $("#jqGridList").jqGrid({
            url: '${listGridURL}',
            datatype: 'json',
            colNames: [
                '${userId}',
                '${userEmail}',
                '${userRole}',
                '${userCreationDate}'
            ],
            colModel: [
                {name:'id', key:true},
                {name:'email'},
                {name:'userRole'},
                {name:'creationDate'}
            ],
            jsonReader: {
                root: "data",
                page: "currentPage",
                total: "totalPages",
                records: "totalRecords",
                repeatitems: false,
                id: "id"
            },
            pager: '#jqGridPager',
            rowNum: 15,
            rowList: [10, 15, 25, 50],
            sortname: 'id',
            sortorder: 'asc',
            autowidth: true,
            shrinkToFit: true,
            width: 'auto',
            height: 'auto',
            regional:
                <c:if test="${empty lang}">'${localeCode}'</c:if>
                <c:if test="${not empty lang}">'${lang}'</c:if>,
            gridview: true,
            styleUI : "Bootstrap",
            onSelectRow: function(id){
                document.location.href = "${usersURL}/" + id;
            }
        });
    });
</script>
