class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "home", action: "index")
        "/login"(controller: "login", action: "auth")
        "/login/authfail"(controller: "login", action: "authfail")
        "/login/denied"(controller: "login", action: "denied")
        "/logout"(controller: "logout")
        "/signup"(controller: "register")
        "/feed/$region"(controller: "region", action: "feed")
        "/register/register"(controller: "register", action: "register")
        "/register/resetPassword"(controller: "register", action: "resetPassword")
        "/register/verifyRegistration"(controller: "register", action: "verifyRegistration")
        "/forgotPassword"(controller: "register", action: "forgotPassword")
        "/region/create"(controller: "region", action: "create")
        "/regions"(controller: "region", action: "list")
        "/region/save"(controller: "region", action: "save")
        "/region/subscribe"(controller: "region", action: "subscribe")
        "/region/deleteSubscription"(controller: "region", action: "deleteSubscription")
        "/route/create"(controller: "route", action: "create")
        "/route/index"(controller: "route", action: "index")
        "/route/save"(controller: "route", action: "save")
        "/direction/create"(controller: "direction", action: "create")
        "/direction/save"(controller: "direction", action: "save")
        "/direction/index"(controller: "direction", action: "index")
        "/rateable/rate"(controller: "rateable", action: [POST: "rate"])
        "/commentable/add"(controller: "commentable", action: [POST: "add"])
        name region: "/$region/$route?" {
            controller = "region"
            action = "find"
        }
        "500"(view: '/error')

    }

    /*
    GET     /                           controllers.ApplicationController.index()

    GET     /signup                     controllers.ApplicationController.signupForm()
    POST    /signup                     controllers.ApplicationController.signup()

    POST    /login                      controllers.ApplicationController.login()

    GET     /feed/:region               controllers.RegionController.getRegionFeed(region)

    POST    /:region/subscribe          controllers.RegionController.subscribe(region)

    GET     /:region/addroute           controllers.RegionController.addRoute(region)
    POST    /:region/addroute           controllers.RegionController.saveRoute(region)

    POST    /:region/:route/rating      controllers.RouteController.saveRating(region, route)

    POST    /:region/:route/comment     controllers.RouteController.saveComment(region, route)

    GET     /:region/:route             controllers.RouteController.getRouteHtml(region, route)

    GET     /:region                    controllers.RegionController.getRegionHtml(region)
     */
}
