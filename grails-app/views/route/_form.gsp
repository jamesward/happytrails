<%@ page import="happytrails.Route" %>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="route.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${routeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="route.description.label" default="Description"/>

    </label>
    <g:textArea rows="5" name="description" value="${routeInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'distance', 'error')} required">
    <label for="distance">
        <g:message code="route.distance.label" default="Distance"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field size="6" style="width: 60px" type="number" step="any" name="distance" required=""
             value="${fieldValue(bean: routeInstance, field: 'distance')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'location', 'error')} required">
    <label for="location">
        <g:message code="route.location.label" default="Location"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="location" required="" value="${routeInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'mapUrl', 'error')} ">
    <label for="mapUrl">
        <g:message code="route.mapUrl.label" default="Map URL"/>

    </label>
    <g:field type="url" style="width: 400px" name="mapUrl" value="${routeInstance?.mapUrl}"/>
</div>

<label><g:message code="route.directions.label" default="Directions"/></label>
<noscript>
    <g:link class="btn btn-mini btn-add btn-success" controller="direction" action="create" style="margin-left: 70px"
            params="['route.id': routeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'direction.label', default: 'Direction')])}</g:link>
</noscript>
<div>
    <table class="table table-condensed" id="directions" style="margin-bottom: 0">
        <thead>
            <th>
                <g:message code="direction.stepNumber.label" default="Step" />
            	<span class="required-indicator">*</span>
            </th>
            <th>
                <g:message code="direction.instruction.label" default="Instruction" />
                <span class="required-indicator">*</span>
            </th>
        </thead>
        <tbody>
        <g:each in="${routeInstance?.directions?}" var="d" status="i">
            <tr id="direction-${d?.id}">
                <td>
                    <g:field type="number" style="width: 40px" name="directions[${i}].stepNumber" required="" value="${fieldValue(bean: d, field: 'stepNumber')}"/>
                </td>
                <td class="nowrap">
                    <g:textField style="width: 400px" name="directions[${i}].instruction" required="" value="${d?.instruction}"/>
                    <g:if test="${d.id}">
                    <g:remoteLink controller="direction" action="delete" id="${d?.id}"
                                  onSuccess="deleteSuccess(data)" onFailure="deleteFailure(data)"><i class="icon-minus-sign"> </i></g:remoteLink>
                    </g:if>
                    <g:else>
                        <a href="#" class="removeDirection"><i class="icon-minus-sign"> </i></a>
                    </g:else>
                </td>
            </tr>
        </g:each>
        <tr style="display: none" id="template">
            <td>
                <g:field type="number" style="width: 40px" name="directions[0].stepNumber"/>
            </td>
            <td>
                <g:textField style="width: 400px" name="directions[0].instruction"/>
                <a href="#" class="removeDirection"><i class="icon-minus-sign"> </i></a>
            </td>
        </tr>
        </tbody>
    </table>
    <g:link class="addDirection" controller="direction" action="create"
        params="['route.id': routeInstance?.id]"><i class="icon-plus-sign"></i>
        ${message(code: 'default.add.label', args: [message(code: 'direction.label', default: 'a Step')])}</g:link>
</div>

<script type="text/javascript">
    $('form').on('submit', function() {
       $('#template').remove();
    });

    $('.removeDirection').on('click', function() {
        $(this).closest('tr').remove();
    });

    function deleteSuccess(data) {
        $('#direction-' + data.id).remove();
        $("<div/>").addClass('alert alert-success').append(data.message).insertAfter('h1');
    }

    function deleteFailure(data) {
        alert('An error occurred, please try again.')
    }

    $(".addDirection").on('click', function(e) {
        e.preventDefault();
        var index = $('#directions tbody>tr').length;
        $('#template').clone(true).insertAfter('#directions tbody>tr:last');
        $("#directions tbody>tr:last").removeAttr('id');
        $.each($('#directions tbody>tr:last input'), function(i, item) {
            var id = $(item).attr('id');

            if (index > 1) {
                $(item).attr('id', id.replace(/[0-9]/, index-1));
                $(item).attr('name', id.replace(/[0-9]/, index-1));
            }

        });
        $('#directions tbody>tr:last').show();
        $('#directions tbody>tr:last input:first').focus();
    });
</script>

<div class="separator"></div>