grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherit false // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        mavenRepo "http://repo.grails.org/grails/core"
    }

    def gebVersion = "0.9.1"
    def seleniumVersion = "2.31.0"
    def spockVersion = "0.7"

    dependencies {
        runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
        test "org.grails:grails-datastore-test-support:1.0.2-grails-2.4"

        test "org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion"
        test "org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion"
        test "org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion"
        test "org.seleniumhq.selenium:selenium-remote-driver:$seleniumVersion"
        test ("com.github.detro.ghostdriver:phantomjsdriver:1.0.1") {
            transitive = false
        }

        test ('dumbster:dumbster:1.6') {
            excludes 'mail', 'activation'
        }

        test "org.gebish:geb-spock:$gebVersion"
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.55"

        // plugins for the compile step
        compile ":scaffolding:2.1.2"
        compile ':cache:1.1.8'
        compile ":asset-pipeline:2.0.13"
        compile ":cache-headers:1.1.7"
        compile ":cloud-bees:0.6.2"
        compile ":commentable:0.8.1"
        compile ":famfamfam:1.0.1"
        compile ":feeds:1.6"
        compile ":jquery-ui:1.10.4"
        compile ":mail:1.0.7"
        compile ":quartz:1.0.2"
        compile ":rateable:0.7.1"
        compile ":searchable:0.6.9"
        compile ":seofriendly-urls:1.0.2"
        compile ":spring-security-core:2.0-RC4"
        compile ":spring-security-ui:1.0-RC2"

        test ":geb:$gebVersion"
        test ":remote-control:1.5"
        test (":spock:$spockVersion") {
            excludes "spock-grails-support"
        }

        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.17"
        runtime ":database-migration:1.4.0"
        runtime ":jquery:1.11.1"

        // Uncomment these to enable additional asset-pipeline capabilities
        //compile ":sass-asset-pipeline:1.9.0"
        //compile ":less-asset-pipeline:1.10.0"
        //compile ":coffee-asset-pipeline:1.8.0"
        //compile ":handlebars-asset-pipeline:1.3.0.3"
    }
}
