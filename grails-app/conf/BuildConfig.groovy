grails.servlet.version = "3.0"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits false // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenLocal()
        mavenCentral()
        mavenRepo "http://repo.grails.org/grails/core"
    }

    def gebVersion = "0.9.1"
    def seleniumVersion = "2.31.0"
    def spockVersion = "0.7"

    dependencies {
        compile 'postgresql:postgresql:9.1-901-1.jdbc4'

        test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") {
            exclude "xml-apis"
        }
        test("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
        test("org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion")
        test "org.seleniumhq.selenium:selenium-remote-driver:$seleniumVersion"
        test("com.github.detro.ghostdriver:phantomjsdriver:1.0.1") {
            transitive = false
        }

        test('dumbster:dumbster:1.6') {
            excludes 'mail', 'activation'
        }

        test "org.gebish:geb-spock:$gebVersion"
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
    }

    plugins {
        compile ":scaffolding:2.0.0"
        compile ':cache:1.1.1'
        compile ":cache-headers:1.1.5"
        compile ":cloud-bees:0.6.2"
        compile ":commentable:0.8.1"
        compile ":famfamfam:1.0.1"
        compile ":feeds:1.6"
        compile ":jquery-ui:1.8.24"
        compile ":mail:1.0.1"
        //compile ":memcached:1.0.3.2"
        compile ":quartz:1.0-RC5"
        compile ":rateable:0.7.1"
        compile ":searchable:0.6.5-SNAPSHOT"
        compile ":seofriendly-urls:1.0.2"
        compile ":spring-security-core:1.2.7.3"
        compile ":spring-security-ui:0.2"

        test ":geb:$gebVersion"
        test ":remote-control:1.4"
        test(":spock:$spockVersion") {
            exclude "spock-grails-support"
        }

        runtime ":hibernate:3.6.10.1" // or ":hibernate4:4.1.11.1"
        runtime ":database-migration:1.3.5"
        runtime ":jquery:1.9.1"
        runtime ":resources:1.2"

        runtime ":zipped-resources:1.0.1"
        runtime ":cached-resources:1.1"
        runtime ":yui-minify-resources:0.1.5"

        build ":tomcat:7.0.41"
    }
}
