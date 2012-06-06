<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Happy Trails!</title>
</head>

<body>

<div class="row-fluid">
    <div class="span12 intro">
        <p>Welcome to Happy Trails, where all your dreams will come true.</p>

        <div class="row-fluid">
            <div class="span10 content">
                <div class="row-fluid">
                    <g:each in="${regions}" status="i" var="r">
                        <g:if test="${(i % 2) == 0}">
                            <div class="span2"></div>
                        </g:if>
                        <div class="span3">
                            <link:region region="${r.seoName}" route="">${fieldValue(bean: r, field: "name")}</link:region>
                        </div>
                        <g:if test="${(i % 2) == 1}">
                            <div class="span2"></div>
                            </div>
                            <div class="row-fluid">
                        </g:if>
                    </g:each>
                </div>
            </div>

            <div class="span2 sidebar">
                <!--Sidebar content-->
                sidebar
            </div>
        </div>
    </div>
</div>

</body>
</html>
