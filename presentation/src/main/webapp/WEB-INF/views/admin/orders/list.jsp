<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<spring:message code="title_orders" var="title"/>
<spring:message code="order_id" var="orderId"/>
<spring:message code="order_status" var="orderStatus"/>
<spring:message code="order_cargoes" var="orderCargoes"/>
<spring:message code="order_truck" var="orderTruck"/>
<spring:message code="order_drivers" var="orderDrivers"/>
<spring:message code="order_route" var="orderRoute"/>
<spring:message code="order_creation_date" var="orderCreationDate"/>
<spring:message code="app_button_edit" var="appButtonEdit" />
<spring:message code="app_button_create" var="appButtonCreate" />
<spring:message code="app_button_refresh" var="appButtonRefresh" />

<spring:url var="ordersURL" value="/admin/orders"/>
<spring:url var="listGridURL" value="${ordersURL}/listgrid"/>

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
                <a class="btn btn-info" role="button" onclick="return sendToCreatingOrder()"
                   data-toggle="modal" data-target="#creating">${appButtonCreate}</a>
                <a class="btn btn-info" role="button" href="${ordersURL}">${appButtonRefresh}</a>
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
                '${orderId}',
                '${orderStatus}'
            ],
            colModel: [
                {name:'id', key:true},
                {name:'status'}
                /*{name:'truck'},
                {name:'route'},
                {name:'creationDate'}*/
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
            rowNum: 25,
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
                document.location.href = "${ordersURL}/" + id;
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
