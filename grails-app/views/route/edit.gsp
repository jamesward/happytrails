<%@ page import="happytrails.Route" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'route.label', default: 'Route')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
        <rateable:resources/>
	</head>
	<body>
        <g:set var="breadcrumb" scope="request">
        <ul class="breadcrumb">
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
            <li><g:link controller="region" action="list">Regions</g:link> <span class="divider">/</span></li>
            <li><g:link uri="/${routeInstance.region.seoName}">${routeInstance.region.name}</g:link> <span class="divider">/</span></li>
            <li>${routeInstance.name}</fieldset>
        </ul>
        </g:set>
		<div id="edit-route" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="alert alert-success" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${routeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${routeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${routeInstance?.id}" />
				<g:hiddenField name="version" value="${routeInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="btn" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>

        <div id="rate-route">
            <rateable:ratings bean='${routeInstance}'/>
        </div>

        <div id="comment-route">
            <g:if test="${routeInstance.id}">
            <div class="fieldcontain ${hasErrors(bean: routeInstance, field: 'comments', 'error')} ">
                <label for="comments">
                    <g:message code="route.comments.label" default="Comments"/>
                </label>
                <comments:render bean="${routeInstance}" />
            </div>
            </g:if>
        </div>
	</body>
</html>
