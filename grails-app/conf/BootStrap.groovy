import happytrails.User

class BootStrap {

    def init = { servletContext ->
        if (!User.count()) {
            new User(username: "mraible@gmail.com", password: "happyhour",
                     name: "Matt Raible", enabled: true).save(failOnError: true)
        }
    }
    def destroy = {
    }
}
