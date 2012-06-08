<%@ page import="happytrails.Region" %>


<div class="fieldcontain ${hasErrors(bean: regionInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="region.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${regionInstance?.name}"/>
</div>


<g:if test="${regionInstance.id}">

    <div class="fieldcontain ${hasErrors(bean: regionInstance, field: 'routes', 'error')} ">
        <label for="routes">
            <g:message code="region.routes.label" default="Routes"/>

        </label>
        <ul class="one-to-many">
            <g:each in="${regionInstance?.routes ?}" var="r">
                <li><g:link controller="route" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="route" action="create"
                        params="['region.id': regionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'route.label', default: 'Route')])}</g:link>
            </li>
        </ul>

    </div>

</g:if>