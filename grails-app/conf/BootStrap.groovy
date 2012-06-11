import happytrails.User
import happytrails.Region
import happytrails.Route
import happytrails.RegionSubscription
import happytrails.Comment
import org.grails.rateable.Rating

class BootStrap {

    def init = { servletContext ->

        if (!User.count()) {
            User user = new User(username: "mraible@gmail.com", password: "happyhour",
                    name: "Matt Raible", enabled: true).save(failOnError: true)
            User commentor = new User(username: "mraible+comments@gmail.com", password: "happyhour",
                    name: "Fitz Raible", enabled: true).save(failOnError: true)

            Region frontRange = new Region(name: "Colorado Front Range").save(failOnError: true)
            // Add routes
            def whiteRanch = new Route(name: "White Ranch", distance: 10, location: "Golden, CO",
                    description: "Long uphill climb", region: frontRange)

            // Add comments
            whiteRanch.addToComments(new Comment(value: "Coming down is the best!", user: commentor))
            whiteRanch.save(failOnError: true)

            // Add a few ratings
            whiteRanch.rate(user, 3)
            whiteRanch.rate(user, 4)
            whiteRanch.rate(user, 5)

            frontRange.addToRoutes(whiteRanch)
            frontRange.addToRoutes(new Route(name: "The Hogback", distance: 3, location: "Morrison, CO",
                    description: "Very Technical, but fun!"))

            frontRange.save(failOnError: true)

            new Region(name: "Colorado Western Slope").save(failOnError: true)
            new Region(name: "Moab").save(failOnError: true)
            new Region(name: "Grand Valley").save(failOnError: true)

            RegionSubscription subscription = new RegionSubscription(user, frontRange)
            user.addToRegionSubscriptions(subscription)
            user.save()
        }
    }
    def destroy = {
    }
}
