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
            def frontRange = new Region(name: "Colorado Front Range").save(failOnError: true)
            // Add routes
            frontRange.routes.add(new Route(name: "White Ranch", description: "Long uphill climb"))
            frontRange.routes.add(new Route(name: "The Hogback", description: "Very Technical, but fun!"))
            frontRange.save(failOnError: true)

            new Region(name: "Colorado Western Slope").save(failOnError: true)
            new Region(name: "Moab").save(failOnError: true)
            new Region(name: "Fruita").save(failOnError: true)
            new Region(name: "California").save(failOnError: true)
            new Region(name: "Montana").save(failOnError: true)
        }
    }
    def destroy = {
    }
}
