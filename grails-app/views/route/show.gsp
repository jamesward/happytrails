
<%@ page import="happytrails.Route" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'route.label', default: 'Route')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
        <g:set var="breadcrumb" scope="request">
        <ul class="breadcrumb">
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
            <li><g:link controller="region" action="list">Regions</g:link> <span class="divider">/</span></li>
            <li><g:link uri="/${routeInstance.region.seoName}">${routeInstance.region.name}</g:link> <span class="divider">/</span></li>
            <li>${routeInstance.name}</li>
        </ul>
        </g:set>
		<div id="show-route" class="content scaffold-show" role="main">
			<h1>${routeInstance.name}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

				<g:if test="${routeInstance?.description}">
				<fieldset class="control-group">
					%{--<span id="description-label" class="property-label"><g:message code="route.description.label" default="Description" /></span>--}%

                    <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${routeInstance}" field="description"/></span>

				</fieldset>
				</g:if>

				<g:if test="${routeInstance?.distance}">
				<fieldset class="control-group">
					<span id="distance-label" class="property-label"><g:message code="route.distance.label" default="Distance" /></span>

						<span class="property-value" aria-labelledby="distance-label"><g:fieldValue bean="${routeInstance}" field="distance"/></span>
					    miles
				</fieldset>
				</g:if>

				<g:if test="${routeInstance?.location}">
				<fieldset class="control-group">
					<span id="location-label" class="property-label"><g:message code="route.location.label" default="Location" /></span>

						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${routeInstance}" field="location"/></span>

				</fieldset>
				</g:if>


				<g:if test="${routeInstance?.photo}">
				<fieldset class="control-group">
					<span id="photo-label" class="property-label"><g:message code="route.photo.label" default="Photo" /></span>

						<span class="property-value" aria-labelledby="photo-label"><g:link controller="photo" action="show" id="${routeInstance?.photo?.id}">${routeInstance?.photo?.encodeAsHTML()}</g:link></span>

				</fieldset>
				</g:if>

				<g:if test="${routeInstance?.mapUrl}">
				<fieldset class="control-group">
					<span id="mapUrl-label" class="property-label"><g:message code="route.mapUrl.label" default="Map" /></span>

						<span class="property-value" aria-labelledby="mapUrl-label"><a href="${routeInstance.mapUrl}"><g:fieldValue bean="${routeInstance}" field="mapUrl"/></a></span>

				</fieldset>
				</g:if>

				<g:if test="${routeInstance?.creationDate}">
				<fieldset class="control-group">
					<span id="creationDate-label" class="property-label"><g:message code="route.creationDate.label" default="Creation Date" /></span>

						<span class="property-value" aria-labelledby="creationDate-label"><g:formatDate date="${routeInstance?.creationDate}" type="datetime" style="LONG" timeStyle="SHORT"/></span>

				</fieldset>
				</g:if>

            <g:if test="${routeInstance?.comments}">
                <fieldset class="control-group">
                    <span id="comments-label" class="property-label">
                        <g:message code="route.comments.label" default="Comments"/></span>
                    <ul class="comments">
                        <g:each in="${routeInstance.comments}" var="c">
                            <li class="property-value" aria-labelledby="comments-label">
                                <g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link>
                            </li>
                        </g:each>
                    </ul>
                </fieldset>
            </g:if>

				<g:if test="${routeInstance?.directions}">
				<fieldset class="control-group">
					<span id="directions-label" class="property-label"><g:message code="route.directions.label" default="Directions" /></span>

						<g:each in="${routeInstance.directions}" var="d">
						<span class="property-value" aria-labelledby="directions-label"><g:link controller="direction" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>

				</fieldset>
				</g:if>
                <g:if test="${routeInstance.averageRating}">
                    <fieldset class="control-group">
                        <span id="averageRating-label" class="property-label"><g:message code="route.averageRating.label" default="Avg. Rating" /></span>
                        <span class="property-value" aria-labelledby="averageRating-label"><g:fieldValue bean="${routeInstance}" field="averageRating"/></span>
                    </fieldset>
                </g:if>
				<g:if test="${routeInstance?.ratings}">
				<fieldset class="control-group">
					<span id="ratings-label" class="property-label"><g:message code="route.ratings.label" default="Ratings" /></span>
                        <ul class="ratings">
						<g:each in="${routeInstance.ratings}" var="r">
						<li class="property-value" aria-labelledby="ratings-label"><g:link controller="rating" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
						</g:each>
                        </ul>
				</fieldset>
				</g:if>

            <sec:ifLoggedIn>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${routeInstance?.id}" />
					<g:link class="edit" class="btn" action="edit" id="${routeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" class="btn" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
            </sec:ifLoggedIn>
		</div>
	</body>
</html>
