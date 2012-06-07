import happytrails.User
import happytrails.Region
import happytrails.Route

class BootStrap {

    def init = { servletContext ->
        if (!User.count()) {
            new User(username: "mraible@gmail.com", password: "happyhour",
                     name: "Matt Raible", enabled: true).save(failOnError: true)
        }
        if (!Region.count()) {
            Region frontRange = new Region(name: "Colorado Front Range").save(failOnError: true)
            // Add routes
            frontRange.addToRoutes(new Route(name: "White Ranch", distance: 10, location: "Golden, CO",
                    description: "Long uphill climb"))
            frontRange.addToRoutes(new Route(name: "The Hogback", distance:  3, location: "Morrison, CO",
                    description: "Very Technical, but fun!"))
            frontRange.save(failOnError: true)

            new Region(name: "Colorado Western Slope").save(failOnError: true)
            new Region(name: "Moab").save(failOnError: true)
            new Region(name: "Grand Valley").save(failOnError: true)
        }
    }
    def destroy = {
    }
}
