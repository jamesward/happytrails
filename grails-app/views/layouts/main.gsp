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
    <title><g:layoutTitle default="Grails"/></title>
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-responsive.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>
<section id="navbar" class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="${createLink(uri: '/')}">Happy Trails</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li <g:if test="${request.forwardURI == request.contextPath + '/'}">class="active"</g:if>><g:link uri="/">Home</g:link></li>
                    <li <g:if test="${request.forwardURI.contains('/regions')}">class="active"</g:if>><g:link uri='/regions'><g:message code="default.list.label" args="['Region']" /></g:link></li>
                    %{--<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li class="nav-header">Nav header</li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>--}%
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
                        <li><g:link controller="logout">Sign Out</g:link></li>
                    <ul>
                </div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <g:if test="${!request.forwardURI.contains('login')}">
                %{--<form class="navbar-form pull-right" method="post" autocomplete="off"
                      action="${request.contextPath}${SpringSecurityUtils.securityConfig.apf.filterProcessesUrl}">
                    <input name="j_username" id="username" type="text" class="span2" placeholder="Username">
                    <input name="j_password" type="password" class="span2" placeholder="Password">
                    <button class="btn btn-primary" style="display: none" type="submit">Login</button>
                </form>--}%
                    <div class="pull-right links"><g:link uri="/login" class="login">Login</g:link> or
                        <g:link uri="/signup" class="signup">Sign Up</g:link></div>
                </g:if>
                </sec:ifNotLoggedIn>
            </div>
            <!-- /.nav-collapse -->
        </div>
    </div>
    <!-- /navbar-inner -->
</section>

${request.breadcrumb}

<div class="container-fluid">
    <g:layoutBody/>
</div>

<g:javascript library="application"/>
<r:layoutResources/>

<script src="${resource(dir: 'js', file: 'jquery-1.7.2.min.js')}"></script>
<script src="${resource(dir: 'js', file: 'bootstrap.min.js')}"></script>
<script>
$(document).ready(function() {
   $('.navbar #username').on('blur', function() {
       if ($(this).val().length > 0) {
           $('.navbar .btn').fadeIn("slow");
       }
   });
});
</script>
</body>
</html>