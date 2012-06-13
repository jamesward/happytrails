<%@ page import="happytrails.Route" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'route.label', default: 'Route')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
        <g:set var="breadcrumb" scope="request">
        <ul class="breadcrumb">
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
            <li><g:link controller="region" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            <li><span class="divider">/</span> <g:link uri="/${routeInstance.region?.seoName}">${routeInstance.region.name}</g:link></li>
            <li><span class="divider">/</span> <g:message code="default.new.label" args="[entityName]" /></li>
        </ul>
        </g:set>

		<div id="create-route" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
            <div class="alert alert-success" role="status">${flash.message}</div>
            </g:if>

			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:hasErrors bean="${routeInstance}">
            <div class="alert alert-error" role="alert">
                <g:eachError bean="${routeInstance}" var="error">
                <div <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></div>
                </g:eachError>
            </div>
			</g:hasErrors>
			<g:form action="save" >
                <g:hiddenField name="region.id" value="${routeInstance?.region?.id}"/>
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="btn btn-primary" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
