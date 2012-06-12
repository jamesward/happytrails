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
    <g:textField name="description" value="${routeInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'distance', 'error')} required">
    <label for="distance">
        <g:message code="route.distance.label" default="Distance"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" step="any" name="distance" required="" value="${fieldValue(bean: routeInstance, field: 'distance')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'location', 'error')} required">
    <label for="location">
        <g:message code="route.location.label" default="Location"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="location" required="" value="${routeInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'region', 'error')} required">
    <label for="region">
        <g:message code="route.region.label" default="Region"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="region" name="region.id" from="${happytrails.Region.list()}" optionKey="id" required=""
              value="${routeInstance?.region?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'photo', 'error')} ">
    <label for="photo">
        <g:message code="route.photo.label" default="Photo"/>

    </label>
    <g:select id="photo" name="photo.id" from="${happytrails.Photo.list()}" optionKey="id"
              value="${routeInstance?.photo?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'mapUrl', 'error')} ">
    <label for="mapUrl">
        <g:message code="route.mapUrl.label" default="Map Url"/>

    </label>
    <g:field type="url" name="mapUrl" value="${routeInstance?.mapUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'creationDate', 'error')} ">
    <label for="creationDate">
        <g:message code="route.creationDate.label" default="Creation Date"/>

    </label>
    <g:datePicker name="creationDate" precision="day" value="${routeInstance?.creationDate}" default="none"
                  noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'seoName', 'error')} ">
    <label for="seoName">
        <g:message code="route.seoName.label" default="Seo Name"/>

    </label>
    <g:textField name="seoName" value="${routeInstance?.seoName}"/>
</div>



<div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'directions', 'error')} ">
    <label for="directions">
        <g:message code="route.directions.label" default="Directions"/>

    </label>
    <g:select name="directions" from="${happytrails.Direction.list()}" multiple="multiple" optionKey="id" size="5"
              value="${routeInstance?.directions*.id}" class="many-to-many"/>
</div>

