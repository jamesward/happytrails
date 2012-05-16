package happytrails

class Photo {

    static belongsTo = [route:Route]

    static constraints = {
        thumbnailUrl blank: false
    }

    URL thumbnailUrl
    URL fullUrl
}
