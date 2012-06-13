package happytrails

class Region {
    static charactersNumbersAndSpaces = /[a-zA-Z0-9 ]+/
    static searchable = true

    static constraints = {
        name blank: false, unique: true, matches: charactersNumbersAndSpaces
        seoName nullable: true
        routes cascade:"all,delete-orphan"
    }

    static hasMany = [ routes:Route ]

    String name
    String seoName

    def beforeValidate() {
        def matcher = (name =~ charactersNumbersAndSpaces)
        if (matcher.matches()) {
            if (!seoName) seoName = name?.asFriendlyUrl()
            for (r in routes) {
                if (!r.seoName) r.seoName = r.name?.asFriendlyUrl()
            }
        }
    }

    String toString() {
        name
    }
}
