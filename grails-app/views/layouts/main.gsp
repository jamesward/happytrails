<%@ page import="org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]><html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]><html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]><html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default=""/> | Über Tracks</title>
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <g:layoutHead/>
    <r:require module="core"/>
    <r:layoutResources/>
</head>

<body>
<section id="navbar" class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="http://ubertracks.com">Über Tracks</a>

            <ul class="nav">
                <li <g:if test="${request.forwardURI == request.contextPath + '/'}">class="active"</g:if>><g:link uri="/">Bike</g:link></li>
                <li <g:if test="${request.forwardURI.contains('/regions')}">class="active"</g:if>><g:link uri='/regions'><g:message code="default.list.label" args="['Region']" /></g:link></li>
            </ul>
            <sec:ifLoggedIn>
            <div class="btn-group pull-right">
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-user"></i> <sec:loggedInUserInfo field="fullName"/>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">Profile</a></li>
                    <li class="divider"></li>
                    <li><g:link uri="/logout">Sign Out</g:link></li>
                </ul>
            </div>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
            <g:if test="${!request.forwardURI.contains('login')}">
                <div class="pull-right links"><g:link uri="/login" class="login">Login</g:link> or
                    <g:link uri="/signup" class="signup">Sign Up</g:link></div>
            </g:if>
            </sec:ifNotLoggedIn>
        </div>
    </div>
    <!-- /navbar-inner -->
</section>

${request.breadcrumb}

<div class="container-fluid">
    <g:layoutBody/>
</div>

<r:layoutResources disposition="footer"/>

</body>
</html>