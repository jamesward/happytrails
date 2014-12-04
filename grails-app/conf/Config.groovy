import happytrails.User
import org.springframework.security.core.context.SecurityContextHolder

grails.app.context = "/"
grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [html: ['text/html', 'application/xhtml+xml'],
        xml: ['text/xml', 'application/xml'],
        text: 'text/plain',
        js: 'text/javascript',
        rss: 'application/rss+xml',
        atom: 'application/atom+xml',
        css: 'text/css',
        csv: 'text/csv',
        all: '*/*',
        json: ['application/json', 'text/json'],
        form: 'application/x-www-form-urlencoded',
        multipartForm: 'multipart/form-data'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart = false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = false

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
        grails.plugin.springsecurity.debug.useFilter = true
    }
    test {
        grails {
            mail {
                port = 1025
            }
        }
    }
    production {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://bike.ubertracks.com"
        grails {
            mail {
                host = "smtp.sendgrid.net"
                port = 587
                props = ["mail.smtp.auth":"true",
                         "mail.smtp.socketFactory.port":"465",
                         "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                         "mail.smtp.socketFactory.fallback":"false"]
                username=System.env.SENDGRID_USERNAME
                password=System.env.SENDGRID_PASSWORD
            }
        }

        grails.assets.url = "http://dke2vwgtydyev.cloudfront.net/static"
    }
}

// log4j configuration
log4j = {
    warn 'grails.plugin.springsecurity.ui.SpringSecurityUiService'
    //info 'grails.plugin.springsecurity.web.filter.DebugFilter'

    error 'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'
}

grails.mail.default.from = "Bike Über Tracks <bike@ubertracks.com>"

grails.plugin.springsecurity.ui.register.emailFrom = grails.mail.default.from
grails.plugin.springsecurity.ui.register.emailSubject = 'Welcome to Über Tracks!'
grails.plugin.springsecurity.ui.forgotPassword.emailFrom = grails.mail.default.from
grails.plugin.springsecurity.ui.forgotPassword.emailSubject = 'Password Reset'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        '/user/**': ['ROLE_ADMIN'],
        '/role/**': ['ROLE_ADMIN'],
        '/registrationCode/**': ['ROLE_ADMIN'],
        '/securityInfo/**': ['ROLE_ADMIN'],
        '/**/edit/**': ['IS_AUTHENTICATED_FULLY']
]

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.fii.rejectPublicInvocations = false
grails.plugin.springsecurity.userLookup.userDomainClassName = 'happytrails.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'happytrails.UserRole'
grails.plugin.springsecurity.authority.className = 'happytrails.Role'

/* Use Spring Security to get Rater and Commentor:
   - http://stackoverflow.com/questions/3541142/grails-user-evaluator-for-commentable-with-spring-security-plugin
*/
grails.rateable.rater.evaluator = {
    def principal = SecurityContextHolder.context.authentication.principal
    if (principal.hasProperty('id')) {
        def currentUserId = principal.id
        if (currentUserId) {
            User.get(currentUserId)
        }
    }
}

grails.commentable.poster.evaluator = {
    def principal = SecurityContextHolder.context.authentication.principal
    if (principal.hasProperty('id')) {
        def currentUserId = principal.id
        if (currentUserId) {
            User.get(currentUserId)
        }
    }
}

// Cloudbees Plugin:
cloudbees.account = 'happytrails'
cloudbees.api.key = '3160AB4C28E2FC90'
cloudbees.api.secret = 'SHSLTMEQEQXKN8RI0IHOTUZVAMUPKGQFBMKGJT9YT7G='

grails.cache.config = {
   cache {
      name 'home'
   }
   cache {
      name 'user'
   }
   cache {
      name 'region'
   }
}

/* Memcached Plugin */
/*grails.plugin.memcached.hosts = System.env.MEMCACHIER_SERVERS
grails.plugin.memcached.username = System.env.MEMCACHIER_USERNAME
grails.plugin.memcached.password = System.env.MEMCACHIER_PASSWORD*/

// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */
