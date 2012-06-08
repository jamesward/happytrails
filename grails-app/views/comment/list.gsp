
<%@ page import="happytrails.Comment" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'comment.label', default: 'Comment')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-comment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-comment" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="value" title="${message(code: 'comment.value.label', default: 'Value')}" />
					
						<g:sortableColumn property="creationDate" title="${message(code: 'comment.creationDate.label', default: 'Creation Date')}" />
					
						<th><g:message code="comment.photo.label" default="Photo" /></th>
					
						<th><g:message code="comment.route.label" default="Route" /></th>
					
						<th><g:message code="comment.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${commentInstanceList}" status="i" var="commentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${commentInstance.id}">${fieldValue(bean: commentInstance, field: "value")}</g:link></td>
					
						<td><g:formatDate date="${commentInstance.creationDate}" /></td>
					
						<td>${fieldValue(bean: commentInstance, field: "photo")}</td>
					
						<td>${fieldValue(bean: commentInstance, field: "route")}</td>
					
						<td>${fieldValue(bean: commentInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${commentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
