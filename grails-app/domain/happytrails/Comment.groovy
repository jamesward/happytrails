package happytrails

class Comment {
    static belongsTo = [route:Route]

    static constraints = {
        value blank: false
        creationDate nullable: true
    }

    User user
    String value
    Photo photo
    Date creationDate

    def beforeInsert() {
        creationDate = new Date()
    }
}
