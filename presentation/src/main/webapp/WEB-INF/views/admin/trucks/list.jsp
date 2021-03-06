<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<spring:message code="title_trucks" var="title"/>
<spring:message code="truck_id" var="truckId" />
<spring:message code="truck_number" var="truckNumber" />
<spring:message code="truck_people" var="truckPeople" />
<spring:message code="truck_active" var="truckActive" />
<spring:message code="truck_capacity" var="truckCapacity" />
<spring:message code="truck_city" var="truckCity" />
<spring:message code="app_button_edit" var="appButtonEdit" />
<spring:message code="app_button_create" var="appButtonCreate" />
<spring:message code="app_button_refresh" var="appButtonRefresh" />

<spring:url var="trucksURL" value="/admin/trucks"/>
<spring:url var="listGridURL" value="${trucksURL}/listgrid"/>

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
                <a class="btn btn-info" role="button" href="${trucksURL}">${appButtonRefresh}</a>
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

<div class="col-xs-12 app-custom-jqgrid-fix">
    <table id="jqGridList" class="table table-striped"></table>
    <div id="jqGridPager"></div>
</div>

<script type="text/javascript">
    $(function(){
        $("#jqGridList").jqGrid({
            url: '${listGridURL}',
            datatype: 'json',
            colNames: [
                '${truckId}',
                '${truckNumber}',
                '${truckPeople}',
                '${truckActive}',
                '${truckCapacity}',
                '${truckCity}'
            ],
            colModel: [
                /*{name:'id', width:75, key:true},
                {name:'name', width:150},
                {name:'weight', width:75},
                {name:'departureCity', width:150},
                {name:'arrivalCity', width:150},
                {name:'status', width:150}*/
                {name:'id', key:true},
                {name:'number'},
                {name:'people'},
                {name:'active'},
                {name:'capacity'},
                {name:'city'}
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
            rowNum: 10,
            rowList: [10, 15, 25, 50],
            sortname: 'id',
            sortorder: 'desc',
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
                document.location.href = "${trucksURL}/" + id;
            }
        });
    });
</script>

<c:if test="${message.type eq 'error'}">
    <script>
        $(document).ready(function(){
            if ($('.alert-danger').length) {
                $('a[data-target="#creating"]').click();
            }
        });
    </script>
</c:if>
