modules = {
    core {
        dependsOn 'jquery'
        defaultBundle 'ui'
        resource url: '/css/bootstrap.min.css'
        resource url: '/css/bootstrap-responsive.min.css'
        resource url: '/css/main.css'
        resource url: '/css/mobile.css'

        resource url: '/js/bootstrap.min.js', disposition: 'footer'
    }
}