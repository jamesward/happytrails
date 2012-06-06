import happytrails.User
import happytrails.Region

class BootStrap {

    def init = { servletContext ->
        if (!User.count()) {
            new User(username: "mraible@gmail.com", password: "happyhour",
                     name: "Matt Raible", enabled: true).save(failOnError: true)
        }
        if (!Region.count()) {
            new Region(name: "Colorado Front Range").save(failOnError: true)
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
