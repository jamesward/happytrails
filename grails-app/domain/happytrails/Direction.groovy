package happytrails

class Direction {

    static belongsTo = [route:Route]
    static constraints = {
        stepNumber blank: false
        instruction blank: false
    }

    Integer stepNumber
    String instruction
    Route route

    String toString() {
        instruction
    }
}
