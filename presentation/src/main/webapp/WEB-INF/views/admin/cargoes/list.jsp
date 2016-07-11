<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message code="title_cargoes" var="titleCargoes"/>
<spring:message code="cargo_id" var="cargoId" />
<spring:message code="cargo_name" var="cargoName" />
<spring:message code="cargo_weight" var="cargoWeight" />
<spring:message code="cargo_departure_city" var="cargoDepartureCity" />
<spring:message code="cargo_arrival_city" var="cargoArrivalCity" />
<spring:message code="cargo_status" var="cargoStatus" />
<spring:message code="app_button_edit" var="appButtonEdit" />
<spring:message code="app_button_create" var="appButtonCreate" />
<spring:message code="app_button_refresh" var="appButtonRefresh" />

<spring:url value="/cargoes/listgrid" var="listGridUrl" />

<c:set var="localeCode" value="${pageContext.response.locale}" />

<div class="row">
    <div class="col-lg-12">

        <div class="panel panel-default">
            <div class="btn-group">
                <a class="btn btn-default" readonly>${titleCargoes}</a>
                <a class="btn btn-info" role="button" href="/cargoes?add">${appButtonCreate}</a>
                <a class="btn btn-info" role="button" href="/cargoes">${appButtonRefresh}</a>
            </div>
        </div>

        <div class="page-header">
            <div class="btn-group">
                <a class="btn btn-default" readonly>${titleCargoes}</a>
                <a class="btn btn-info" role="button" href="/cargoes?add">${appButtonCreate}</a>
                <a class="btn btn-info" role="button" href="/cargoes">${appButtonRefresh}</a>
            </div>
        </div>

    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <table id="jqGridList" class="table table-striped"></table>
        <div id="jqGridPager"></div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        $("#jqGridList").jqGrid({
            url: '${listGridUrl}',
            datatype: 'json',
            colNames: [
                '${cargoId}',
                '${cargoName}',
                '${cargoWeight}',
                '${cargoDepartureCity}',
                '${cargoArrivalCity}',
                '${cargoStatus}'
            ],
            colModel: [
                {name:'id', width:75, key:true},
                {name:'name', width:150},
                {name:'weight', width:75},
                {name:'departureCity', width:150},
                {name:'arrivalCity', width:150},
                {name:'status', width:150}
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
            /*autowidth: true,*/
            width: 'auto',
            height: 'auto',
            regional:
                <c:if test="${empty lang}">'${localeCode}'</c:if>
                <c:if test="${not empty lang}">'${lang}'</c:if>,
            gridview: true,
            styleUI : "Bootstrap",
            onSelectRow: function(id){
                document.location.href = "/cargoes/" + id;
            }
        });
    });
</script>
