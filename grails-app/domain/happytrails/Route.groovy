package happytrails

class Route {
    static belongsTo = [region:Region]
    static hasMany = [comments:Comment,directions:Direction,ratings:Rating]

    static constraints = {
        name blank: false, unique: true
        distance blank: false
        location blank: false
        region blank: false
        photo nullable: true
        mapUrl nullable: true
        creationDate nullable: true
        seoName unique: true
    }

    String name
    String seoName
    Double distance
    Region region
    String location
    List<Direction> directions
    URL mapUrl
    List<Rating> ratings
    List<Comment> comments
    Photo photo
    Date creationDate

    def beforeInsert() {
        creationDate = new Date()
    }

    def beforeValidate() {
        if (!seoName) seoName = name?.asFriendlyUrl()
    }
}
