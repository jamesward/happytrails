
<%@ page import="happytrails.Region" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'region.label', default: 'Region')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
        <g:set var="breadcrumb" scope="request">
        <ul class="breadcrumb">
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
            <li><g:link controller="region" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            <sec:ifLoggedIn><li><span class="divider">/</span> <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li></sec:ifLoggedIn>
        </ul>
        </g:set>
		<div id="show-region" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list region">
			
				<g:if test="${regionInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="region.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${regionInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${regionInstance?.id}" />
					<g:link class="edit" action="edit" id="${regionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
