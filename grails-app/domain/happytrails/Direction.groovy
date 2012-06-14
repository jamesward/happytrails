package happytrails

class Direction {

    static belongsTo = [route:Route]
    static constraints = {
        stepNumber blank: false, min: 1, max: 50
        instruction blank: false
    }

    Integer stepNumber
    String instruction
    Route route

    String toString() {
        instruction
    }
}
