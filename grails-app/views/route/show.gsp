
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
            <li><g:link uri="/${routeInstance.region?.seoName}">${routeInstance.region?.name}</g:link> <span class="divider">/</span></li>
            <li>${routeInstance.name}</li>
        </ul>
        </g:set>
        <div class="row-fluid">
		<div id="show-route" class="content span4" role="main">
            <g:if test="${flash.message}">
                <div class="alert alert-success" role="status">${flash.message}</div>
            </g:if>

			<h1>${routeInstance.name}</h1>

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

				<g:if test="${routeInstance?.creationDate}">
				<fieldset class="control-group">
					<span id="creationDate-label" class="property-label"><g:message code="route.creationDate.label" default="Created" /></span>

						<span class="property-value" aria-labelledby="creationDate-label"><g:formatDate date="${routeInstance?.creationDate}" type="datetime" style="LONG" timeStyle="SHORT"/></span>

				</fieldset>
				</g:if>

				<g:if test="${routeInstance?.directions}">
				<fieldset class="control-group">
					<span id="directions-label" class="property-label"><g:message code="route.directions.label" default="Directions" /></span>
                        <br/>
                        <ol>
						<g:each in="${routeInstance.directions}" var="d">
						<li class="property-value" aria-labelledby="directions-label">
                            ${d.instruction?.encodeAsHTML()}
                        </li>
                        </ol>
						</g:each>

				</fieldset>
				</g:if>
                <fieldset class="control-group">
                    <span id="averageRating-label" class="property-label"><g:message code="route.averageRating.label" default="Average Rating" /></span>
                    <span class="property-value" aria-labelledby="averageRating-label"><g:fieldValue bean="${routeInstance}" field="averageRating"/></span>
                </fieldset>
				<g:if test="${routeInstance?.ratings}">
				<fieldset class="control-group">
					<span id="ratings-label" class="property-label"><g:message code="route.ratings.label" default="Ratings" /></span>
                        <ul class="ratings">
						<g:each in="${routeInstance.ratings}" var="r">
						<li class="property-value" aria-labelledby="ratings-label">${r?.encodeAsHTML()}</li>
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
            <sec:ifNotLoggedIn>
                <g:link uri="/login" class="btn btn-success">Login</g:link> if you'd like to rate or comment on this route.
            </sec:ifNotLoggedIn>
            </div>

            <div class="span6">
                <g:if test="${routeInstance?.mapUrl}">
                <div id="map">
                    <h3>Map</h3>
                    <iframe width="300" height="300" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
                            src="${routeInstance.mapUrl}&amp;output=embed"></iframe><br /><small>
                    <a href="${routeInstance.mapUrl}">View Larger Map</a>
                </div>
                </g:if>
                <div id="comment-route">
                    <g:if test="${routeInstance.comments}">
                    <h3>Comments</h3>
                        <ul class="comments">
                        <comments:each bean="${routeInstance}">
                             <li>${comment.body} <em>-- ${comment.poster}</em></li>
                        </comments:each>
                        </ul>
                    </g:if>
                </div>
            </div>
        </div>
	</body>
</html>
