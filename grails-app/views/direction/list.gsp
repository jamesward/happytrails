
<%@ page import="happytrails.Direction" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'direction.label', default: 'Direction')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-direction" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-direction" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="stepNumber" title="${message(code: 'direction.stepNumber.label', default: 'Step Number')}" />
					
						<g:sortableColumn property="instruction" title="${message(code: 'direction.instruction.label', default: 'Instruction')}" />
					
						<th><g:message code="direction.route.label" default="Route" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${directionInstanceList}" status="i" var="directionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${directionInstance.id}">${fieldValue(bean: directionInstance, field: "stepNumber")}</g:link></td>
					
						<td>${fieldValue(bean: directionInstance, field: "instruction")}</td>
					
						<td>${fieldValue(bean: directionInstance, field: "route")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${directionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
