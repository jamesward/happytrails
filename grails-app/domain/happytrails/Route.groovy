package happytrails

class Route {

    static constraints = {
    }

    Long id
    String name
    String distance
    Region region
    String location
    List<Direction> directions
    URL mapUrl
    List<Rating> ratings
    List<Comment> comments
    Photo photo
    Date creationDate
}
