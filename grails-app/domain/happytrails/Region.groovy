package happytrails

class Region {

    static constraints = {
        name blank: false, unique: true
        seoName unique: true
        routes cascade:"all,delete-orphan"
    }

    static hasMany = [ routes:Route ]

    String name
    String seoName

    def beforeValidate() {
        if (!seoName) seoName = name?.asFriendlyUrl()
        for (r in routes) {
            if (!r.seoName) r.seoName = r.name?.asFriendlyUrl()
        }
    }

    String toString() {
        name
    }
}
