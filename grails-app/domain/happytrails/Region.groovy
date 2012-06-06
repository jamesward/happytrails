package happytrails

class Region {

    static constraints = {
        name blank: false, unique: true
        seoName unique: true
    }

    String name
    String seoName

    def beforeValidate() {
        if (!seoName) seoName = name?.asFriendlyUrl()
    }
}
