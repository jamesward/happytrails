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
}
