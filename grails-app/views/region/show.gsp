
<%@ page import="happytrails.Region" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'region.label', default: 'Region')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
        <feed:meta kind="atom" version="1.0" controller="region" action="feed"/>
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
			<g:if test="${flash.message}">
			<div class="alert alert-success" role="status">${flash.message}</div>
			</g:if>

			<h1>${regionInstance.name}</h1>
			<g:form>
				<fieldset style="position: absolute">
					<g:hiddenField name="id" value="${regionInstance?.id}" />
					<g:link class="edit" action="edit" id="${regionInstance?.id}" class="btn btn-mini"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" class="btn btn-mini" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>

            <g:if test="${regionInstance.routes.size() > 0}">
            <h3 style="margin-top: 40px">Routes Available</h3>
            <ul>
                <g:each in="${regionInstance.routes}" status="i" var="route">
                    <li><link:region region="${regionInstance.seoName}" route="${route.seoName}">${route.name}</link:region></li>
                </g:each>
            </ul>
            </g:if>
		</div>
	</body>
</html>
