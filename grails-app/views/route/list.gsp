
<%@ page import="happytrails.Route" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'route.label', default: 'Route')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-route" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-route" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'route.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'route.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="distance" title="${message(code: 'route.distance.label', default: 'Distance')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'route.location.label', default: 'Location')}" />
					
						<th><g:message code="route.region.label" default="Region" /></th>
					
						<th><g:message code="route.photo.label" default="Photo" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${routeInstanceList}" status="i" var="routeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${routeInstance.id}">${fieldValue(bean: routeInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: routeInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: routeInstance, field: "distance")}</td>
					
						<td>${fieldValue(bean: routeInstance, field: "location")}</td>
					
						<td>${fieldValue(bean: routeInstance, field: "region")}</td>
					
						<td>${fieldValue(bean: routeInstance, field: "photo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${routeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
