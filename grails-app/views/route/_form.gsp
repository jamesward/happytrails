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

<g:if test="${routeInstance.id}">
<label><g:message code="route.directions.label" default="Directions"/></label>
<ul class="one-to-many">
    <g:each in="${routeInstance?.directions?}" var="d">
        <li><g:link controller="direction" action="show" id="${d.id}">
            ${d.stepNumber?.encodeAsHTML()} - ${d.instruction?.encodeAsHTML()}</g:link>
        </li>
    </g:each>
    <li class="add">
        <g:link controller="direction" action="create"
                params="['route.id': routeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'direction.label', default: 'Direction')])}</g:link>
    </li>
</ul>
</g:if>