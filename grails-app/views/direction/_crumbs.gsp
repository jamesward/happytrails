<g:set var="breadcrumb" scope="request">
<ul class="breadcrumb">
    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a> <span class="divider">/</span></li>
    <li><g:link controller="region" action="list">Regions</g:link> <span class="divider">/</span></li>
    <li><g:link uri="/${directionInstance.route.region.seoName}">${directionInstance.route.region.name}</g:link> <span class="divider">/</span></li>
    <li><g:link uri="/${directionInstance.route.region.seoName}/${directionInstance.route.seoName}">${directionInstance.route.name}</g:link> <span class="divider">/</span></li>
    <li><g:message code="default.create.label" args="[entityName]" /></li>
</ul>
</g:set>