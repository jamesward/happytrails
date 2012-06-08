package happytrails

class Comment {
    static belongsTo = [route:Route]

    static constraints = {
        value blank: false
        creationDate nullable: true
        photo nullable: true
        // todo: figure out how mock these in controller tests, so they can be required
        route nullable: true
        user nullable: true
    }

    User user
    String value
    Photo photo
    Date creationDate

    def beforeInsert() {
        creationDate = new Date()
    }

    String toString() {
        return value + ((user) ? " --" + user.getName() : "")
    }
}
