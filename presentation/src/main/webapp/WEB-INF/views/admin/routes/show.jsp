<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:message code="title_passport_route" var="titleRoutePassport"/>
<spring:message code="next_route_point" var="nextRoutePoint"/>
<spring:message code="route_id" var="routeId" />
<spring:message code="route_duration" var="routeDuration" />
<spring:message code="route_distance" var="routeDistance" />
<spring:message code="route_cities" var="routeCities" />
<spring:message code="app_button_save" var="appButtonSave"/>
<spring:message code="app_button_cancel" var="appButtonCancel"/>
<spring:message code="app_button_add" var="appButtonAdd"/>
<spring:message code="app_button_clear" var="appButtonClear"/>

<c:set var="id" value="id"/>
<c:set var="duration" value="duration"/>
<c:set var="distance" value="distance"/>
<c:set var="cities" value="cities"/>

<c:set var="formClass" value="form-horizontal"/>
<c:set var="outerDivClass" value="row form-group"/>
<%--<c:set var="innerDivClass" value="col-lg-4"/>--%>
<%--<c:set var="buttonDivClass" value="col-lg-offset-3 col-lg-9"/>--%>
<%--<c:set var="labelClass" value="control-label col-lg-3"/>--%>
<%--<c:set var="headerClass" value="col-lg-offset-1 col-lg-11"/>--%>
<c:set var="inputClass" value="form-control"/>
<c:set var="errorsClass" value="control-label text-danger"/>
<c:set var="buttonClass" value="btn btn-default"/>
<c:set var="buttonWideClass" value="btn btn-default btn-block"/>

<c:set var="headerClass" value="col-lg-offset-4 col-lg-4
                                col-md-offset-4 col-md-4
                                col-sm-offset-3 col-sm-6
                                col-xs-offset-2 col-sm-8"/>
<c:set var="mapDivClass" value="col-lg-offset-1 col-lg-10
                                col-md-12
                                col-sm-12
                                col-xs-12"/>
<c:set var="labelClass" value="control-label
                               col-lg-offset-1 col-lg-3
                               col-md-4
                               col-sm-4
                               col-xs-12"/>
<c:set var="innerDivClass" value="col-lg-4
                                  col-md-4
                                  col-sm-4
                                  col-xs-12"/>
<c:set var="buttonDivClass" value="col-xs-offset-4 col-xs-4"/>

<spring:url var="routesURL" value="/admin/routes"/>
<spring:url var="cancelURL" value="${routesURL}"/>

<c:if test="${message.type eq 'error'}">
    <div class="alert alert-danger"><strong>${message.entry}</strong></div>
</c:if>

<form:form method="post" modelAttribute="route" cssClass="${formClass}" role="form" id="routeForm">

    <div class="${outerDivClass}" align="center">
        <label class="${headerClass}">
            <h3>${titleRoutePassport}
                <c:if test="${route.id != 0}"> <kbd>#${route.id}</kbd></c:if>
            </h3>
        </label>
    </div>

    <div class="${outerDivClass}">
        <div class="${mapDivClass} panel panel-default">
            <div id="map" class="YMaps-layer-container"></div>
        </div>
        <div class="${buttonDivClass}">
            <h3 id="yandexLabel" class="label label-warning" hidden></h3>
        </div>
    </div>

    <c:if test="${route.id != 0}">
        <div class="${outerDivClass}">
            <form:label path="${id}" cssClass="${labelClass}">${routeId}:</form:label>
            <div class="${innerDivClass}">
                <form:input path="${id}" cssClass="${inputClass}" value="${route.id}" readonly="true"/>
            </div>
            <div><form:errors path="${id}" cssClass="${errorsClass}"/></div>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <form:label path="${distance}" cssClass="${labelClass}">${routeDistance}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${distance}" cssClass="${inputClass}" name="${distance}"
                        id="distanceForm" readonly="true"/>
        </div>
        <form:errors path="${distance}" cssClass="${errorsClass}" for="${distance}"/>
    </div>

    <div class="${outerDivClass}">
        <form:label path="${duration}" cssClass="${labelClass}">${routeDuration}:</form:label>
        <div class="${innerDivClass}">
            <form:input path="${duration}" cssClass="${inputClass}" name="${duration}"
                        id="durationForm" readonly="true"/>
        </div>
        <form:errors path="${duration}" cssClass="${errorsClass}" for="${duration}"/>
    </div>

    <c:if test="${route.id == 0}">
        <div class="${outerDivClass}">
            <label for="inputForm" class="${labelClass}">${nextRoutePoint}:</label>
            <div class="${innerDivClass}">
                <textarea id="inputForm" class="${inputClass}"
                          rows="1" title="Selected Point"></textarea>
            </div>
        </div>

        <div class="${outerDivClass}">
            <div class="${buttonDivClass}">
                <button class="${buttonClass}" id="add" onclick="addRoutePoint()"
                        type="button">${appButtonAdd}</button>
                <button class="${buttonClass}" id="clear" onclick="clearRoutePoints()"
                        type="button">${appButtonClear}</button>
            </div>
        </div>
    </c:if>

    <div class="${outerDivClass}">
        <label for="outputForm" class="${labelClass}">${routeCities}:</label>
        <div class="${innerDivClass}">
            <textarea id="outputForm" class="${inputClass}"
                      rows="5" title="Route Points" readonly></textarea>
        </div>
    </div>

    <div class="${outerDivClass}">
        <div class="${buttonDivClass}">
            <c:if test="${route.id == 0}">
                <button class="${buttonClass}" type="submit"
                        id="saveButton" disabled>${appButtonSave}</button>
            </c:if>
            <button class="${buttonClass}" data-dismiss="modal">${appButtonCancel}</button>
        </div>
    </div>

    <div class="${outerDivClass}" hidden>
        <table id="tableWithHiddenForms"><tr><td></td></tr></table>
    </div>

</form:form>

<script>
    ymaps.ready(init);

    var myMap;
    var myCollection;
    var routePoints = [];

    var saveButton = $('#saveButton');
    var yandexLabel = $('#yandexLabel');
    var distanceForm = $('#distanceForm');
    var durationForm = $('#durationForm');
    var inputForm = $('#inputForm');
    var outputForm = $('#outputForm');
    var tableWithHiddenForms = $('#tableWithHiddenForms');

    function init(){
        myMap = new ymaps.Map ("map", {
            center: [59.94021058, 30.31381775],
            zoom: 7
        });
        myCollection = new ymaps.GeoObjectCollection();
        myMap.geoObjects.add(myCollection);
    }

    function createRoute() {
        ymaps.route(routePoints, { mapStateAutoApply: true }).then(
                function (route) {
                    saveButton.removeAttr('disabled');
                    yandexLabel.hide();
                    myCollection.add(route);
                    distanceForm.val(Math.round(route.getLength() / 1000));
                    durationForm.val(Math.round(route.getTime() / 3600));
                    inputForm.val('');
                },
                function (error) {
                    saveButton.prop('disabled', 'true');
                    yandexLabel.text('Yandex.Map Error: ' + error.message);
                    yandexLabel.show();
                    inputForm.val('');
                }
        );
    }

    function addRoutePoint() {
        routePoints.push(inputForm.val());
        createRoute(routePoints);
        outputForm.val(outputForm.val() + routePoints.length + '. ' + inputForm.val() + '\n');
        addHiddenInputForm();
    }

    function clearRoutePoints() {
        saveButton.prop('disabled', 'true');
        routePoints = [];
        yandexLabel.hide();
        myCollection.removeAll();
        outputForm.val('');
        inputForm.val('');
        distanceForm.val('');
        durationForm.val('');
        clearHiddenInputForms();
    }

    function addHiddenInputForm() {
        $('#tableWithHiddenForms tr:last').after(
                '<tr><td><input ' +
                'type="text" ' +
                'name="route_points" ' +
                'class="hiddenInputForm" ' +
                'value="' + inputForm.val('') + '" />' +
                '</td></tr>');
    }

    function clearHiddenInputForms() {
        $('.routePoints').remove();
    }
</script>

<script>
    /*$('#routeForm').validate();*/
</script>
