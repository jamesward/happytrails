package happytrails

class Rating {

    static constraints = {
    }

    User user
    Integer value
    Date creationDate

    def beforeInsert() {
        creationDate = new Date()
    }
}
