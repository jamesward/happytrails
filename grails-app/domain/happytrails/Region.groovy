package happytrails

class Region {

    static constraints = {
        name blank: false, unique: true
    }

    String name
}
