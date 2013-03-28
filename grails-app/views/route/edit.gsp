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
            <li>${routeInstance.name}</li>
        </ul>
        </g:set>
    <div class="row-fluid">
		<div id="edit-route" class="content span4" role="main">
			<h1>Edit ${routeInstance.name}</h1>
			<g:if test="${flash.message}">
			<div class="alert alert-success" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${routeInstance}">
			<div class="alert alert-error" role="alert">
				<g:eachError bean="${routeInstance}" var="error">
				<div <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></div>
				</g:eachError>
			</div>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${routeInstance?.id}" />
				<g:hiddenField name="version" value="${routeInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit id="updateBtn" class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="btn" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>

        <div class="span6">
            <div id="rate-route">
                <h3>Rating</h3>
                %{--todo: don't allow anonymous users to rate--}%
                <rateable:ratings bean='${routeInstance}'/>
            </div>

            <div id="comment-route">
                <g:if test="${routeInstance.id}">
                <h3>Comments</h3>
                <sec:ifLoggedIn>
                    <comments:render bean="${routeInstance}" />
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <comments:each bean="${routeInstance}">
                         "${comment.body}" <em>-- ${comment.poster}</em><br/>
                    </comments:each>
                </sec:ifNotLoggedIn>
                </g:if>
            </div>
        </div>
    </div>
	</body>
</html>
