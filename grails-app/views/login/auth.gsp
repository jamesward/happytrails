<html>
<head>
    <meta name='layout' content='main'/>
    <title><g:message code="springSecurity.login.title"/></title>
    <style type='text/css' media='screen'>
    h2 {
        margin-bottom: 15px;
    }

    .login-form {
        margin-left: 65px;
        width: 400px;
    }

    legend {
        margin-right: -50px;
        font-weight: bold;
        color: #404040;
    }
    </style>
</head>

<body>
<div class='row'>
    <div class="login-form">
        <h2><g:message code="springSecurity.login.header"/></h2>

        <form action='${postUrl}' method='post' id='loginForm' autocomplete="off">

            <g:if test='${flash.message}'>
                <div class="alert alert-error fade in">${flash.message}</div>
            </g:if>

            <fieldset class="control-group">
                <input type="text" name='j_username' id='username' placeholder="Username">
            </fieldset>
            <fieldset class="control-group">
                <input type="password" name='j_password' id='password' placeholder="Password">
            </fieldset>
            <fielset class="control-group">
                <label class="checkbox">
                    <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
                           <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                    <g:message code="springSecurity.login.remember.me.label"/></label>
            </fielset>
            <fieldset class="form-actions">
                <button class="btn btn-primary" type="submit">Sign in</button>
                <g:link controller="home" class="btn">Cancel</g:link>
            </fieldset>
        </form>
    </div>

</div>
</div>
<script type='text/javascript'>
    <!--
    (function () {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>
</body>
</html>
