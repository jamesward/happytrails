package happytrails

class Route {
    static searchable = true
    static belongsTo = [region:Region]
    static hasMany = [comments:Comment,directions:Direction,ratings:Rating]

    static constraints = {
        name blank: false, unique: true
        description nullable: true
        distance blank: false
        location blank: false
        region blank: false
        photo nullable: true
        mapUrl url: true, nullable: true
        creationDate nullable: true
        seoName unique: true
    }

    String name
    String description
    String seoName
    Double distance
    Region region
    String location
    String mapUrl
    Photo photo
    Date creationDate

    def beforeInsert() {
        creationDate = new Date()
    }

    def beforeValidate() {
        if (!seoName) seoName = name?.asFriendlyUrl()
    }
}
