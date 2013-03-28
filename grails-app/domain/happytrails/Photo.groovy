package happytrails

class Photo {

    static belongsTo = [route:Route]

    static constraints = {
        thumbnailUrl blank: false
    }

    static mapping = {
        cache true
    }

    URL thumbnailUrl
    URL fullUrl
}
