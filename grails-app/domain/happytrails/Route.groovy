package happytrails

import org.grails.comments.Commentable
import org.grails.rateable.Rateable

class Route implements Rateable, Commentable {
    static charactersNumbersAndSpaces = /[a-zA-Z0-9 ]+/
    static searchable = true
    static belongsTo = [region: Region]
    static hasMany = [directions: Direction]
    static transients = ["averageRating"]

    static constraints = {
        name blank: false, unique: true, matches: charactersNumbersAndSpaces
        description nullable: true
        distance blank: false, min: 0.1D
        location blank: false
        region blank: false
        photo nullable: true
        mapUrl url: true, nullable: true
        creationDate nullable: true
        seoName nullable: true
    }

    static mapping = {
        directions sort: 'stepNumber'
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
        def matcher = (name =~ charactersNumbersAndSpaces)
        if (matcher.matches()) {
            if (!seoName) seoName = name?.asFriendlyUrl()
        }
    }
}
