
<%@ page import="happytrails.Region" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'region.label', default: 'Region')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
        <g:set var="breadcrumb" scope="request">
        <ul class="breadcrumb">
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
            <li><g:link controller="region" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            <sec:ifLoggedIn><li><span class="divider">/</span> <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li></sec:ifLoggedIn>
        </ul>
        </g:set>

		<div id="list-region" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-condensed">
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'region.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${regionInstanceList}" status="i" var="regionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${regionInstance.id}">${fieldValue(bean: regionInstance, field: "name")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${regionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
