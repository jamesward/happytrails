
<%@ page import="happytrails.Region" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'region.label', default: 'Region')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
        <link rel="alternate" type="application/atom+xml" title="${regionInstance.name} Updates"
              href="${createLink(controller: 'region', action: 'feed', params: [region: regionInstance.seoName])}"/>
	</head>
	<body>
        <g:set var="breadcrumb" scope="request">
        <ul class="breadcrumb">
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
            <li><g:link controller="region" action="list">Regions</g:link> <span class="divider">/</span></li>
            <li>${regionInstance.name}</li>
        </ul>
        </g:set>
		<div id="show-region" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
			<div class="alert alert-success" role="status">${flash.message}</div>
			</g:if>

			<h1>${regionInstance.name}</h1>
            <sec:ifLoggedIn>
			<g:form>
				<fieldset style="position: absolute">
					<g:hiddenField name="id" value="${regionInstance?.id}" />
					<g:link class="edit" action="edit" id="${regionInstance?.id}" class="btn btn-mini"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" class="btn btn-mini" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
            </sec:ifLoggedIn>
            <g:if test="${regionInstance.routes.size() > 0}">
            <h3 style="margin-top: 40px">Routes Available</h3>
            <table class="routes table table-condensed" style="width: 600px">
                <thead>
                    <tr>
                        <g:sortableColumn property="name" params="[id: regionInstance.id]" action="show"
                                          title="${message(code: 'route.name.label', default: 'Name')}" />
                        <g:sortableColumn property="description" params="[id: regionInstance.id]" action="show"
                                          title="${message(code: 'route.description.label', default: 'Description')}" />
                        <g:sortableColumn property="distance" params="[id: regionInstance.id]" action="show"
                                          title="${message(code: 'route.distance.label', default: 'Distance')}" />
                        <g:sortableColumn property="location" params="[id: regionInstance.id]" action="show"
                                          title="${message(code: 'route.location.label', default: 'Location')}" />
                        <g:sortableColumn property="averageRating" params="[id: regionInstance.id]" action="show"
                                          title="${message(code: 'route.location.label', default: 'Avg. Rating')}"
                                          defaultOrder="desc"/>
                    </tr>
                </thead>
                <tbody>
                <g:each in="${regionInstance.routes}" status="i" var="route">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><link:region region="${regionInstance.seoName}" route="${route.seoName}">${route.name}</link:region></td>
                        <td>${fieldValue(bean: route, field: "description")}</td>
                        <td>${fieldValue(bean: route, field: "distance")}</td>
                        <td>${fieldValue(bean: route, field: "location")}</td>
                        <td>${fieldValue(bean: route, field: "averageRating")}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            </g:if>
		</div>

        <div id="subscribe" class="subscribe">
            <a class="atom" href="${createLink(controller: 'region', action: 'feed', params: [region: regionInstance.seoName])}">
                <g:img dir="images" file="feed-icon-14x14.png" width="14" height="14" class="icon-feed"/> Subscribe via Atom</a>
            <div class="spacer"></div>
            <g:if test="${subscriptionId}">
                You are subscribed for updates in this region.<br/>
                <g:link controller="region" action="deleteSubscription" params="[id: subscriptionId]">Delete</g:link>
            </g:if>
            <g:else>
                <g:form controller="region" action="subscribe">
                    <label for="email">Subscribe to Region for updates:</label>
                    <input type="email" id="email" size="30" placeholder="Your Email Address"><br/>
                    <input type="hidden" name="id" value="${regionInstance.id}"/>
                    <button type="submit" class="btn btn-success">Subscribe</button>
                </g:form>
            </g:else>
        </div>
	</body>
</html>
