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
                        <div class="span3 ${r.seoName} region">
                            <span class="title"><link:region region="${r.seoName}" route="">${r.name}</link:region></span>
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
                <g:if test="${comments.size() > 0}"><h2>Recent Comments</h2></g:if>
                <g:each in="${comments}" status="i" var="c">
                    <span class="comment">${c.body}" <em>--${c.poster}</em></span><br/>
                </g:each>
            </div>
        </div>
    </div>
</div>

</body>
</html>
