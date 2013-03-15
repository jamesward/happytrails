grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

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
    }

    def gebVersion = "0.9.0-RC-1"
    def seleniumVersion = "2.27.0"
    def spockVersion = "0.7"

    dependencies {
        compile 'postgresql:postgresql:9.1-901-1.jdbc4'

        test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") {
            exclude "xml-apis"
        }
        test("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
        test("org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion")

        test('dumbster:dumbster:1.6') {
            excludes 'mail', 'activation'
        }

        test "org.gebish:geb-spock:$gebVersion"
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
    }

    plugins {
        compile ":cache-headers:1.1.5"
        compile ":cloud-bees:0.6.2"
        compile ":commentable:0.8.1"
        compile ":famfamfam:1.0.1"
        compile ":feeds:1.5"
        compile ":jquery-ui:1.8.24"
        compile ":mail:1.0.1"
        compile ":quartz:1.0-RC5"
        compile ":rateable:0.7.1"
        compile ":searchable:0.6.4"
        compile ":seofriendly-urls:1.0.2"
        compile ":spring-security-core:1.2.7.3"
        compile ":spring-security-ui:0.2"

        test ":geb:$gebVersion"
        test(":spock:$spockVersion") {
            exclude "spock-grails-support"
        }

        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.3"
        runtime ":resources:1.1.6"

        runtime ":zipped-resources:1.0"
        runtime ":cached-resources:1.0"
        runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"
    }
}
