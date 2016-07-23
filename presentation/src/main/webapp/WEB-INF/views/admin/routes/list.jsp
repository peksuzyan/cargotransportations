<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<spring:message code="title_routes" var="title"/>
<spring:message code="route_id" var="routeId" />
<spring:message code="route_duration" var="routeDuration" />
<spring:message code="route_distance" var="routeDistance" />
<spring:message code="route_cities" var="routeCities" />
<spring:message code="app_button_edit" var="appButtonEdit" />
<spring:message code="app_button_create" var="appButtonCreate" />
<spring:message code="app_button_refresh" var="appButtonRefresh" />

<spring:url var="routesURL" value="/admin/routes"/>
<spring:url var="listGridURL" value="${routesURL}/listgrid"/>

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
                <a class="btn btn-info" role="button" href="${routesURL}">${appButtonRefresh}</a>
            </div>
        </div>
    </div>
</div>

<div class="col-xs-12">
    <div id="creating" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <tiles:insertAttribute name="modal_admin" />
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
                '${routeId}',
                '${routeDistance}',
                '${routeDuration}',
                '${routeCities}'
            ],
            colModel: [
                {name:'id', key:true},
                {name:'distance'},
                {name:'duration'},
                {name:'cities'}
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
                document.location.href = "${routesURL}/" + id;
            }
        });
    });
</script>
