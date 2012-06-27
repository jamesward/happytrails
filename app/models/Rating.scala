package models;

import java.util.Date
import play.api.Play.current
import net.vz.mongodb.jackson.{Id, ObjectId}
import org.codehaus.jackson.annotate.JsonProperty
import play.modules.mongodb.jackson.MongoDB
import reflect.BeanProperty
;

class Rating(@ObjectId @Id val id: String,
             @BeanProperty @JsonProperty("value") val value: Int,
             @BeanProperty @JsonProperty("creationDate") val creationDate: Date,
             @BeanProperty @JsonProperty("route") val route: Route,
             @BeanProperty @JsonProperty("user") val user: User) {
  @ObjectId @Id def getId = id;
}

object Rating {
  private lazy val db = MongoDB.collection("ratings", classOf[Rating], classOf[String])

  def create(rating: Rating) { db.save(rating) }
  def findAll() = { db.find().toArray }
}

/*

@Entity
public class Rating extends Model {

    @Id
    public Long id;

    @Constraints.Min(1)
    @Constraints.Max(5)
    @Column(nullable = false)
    @Constraints.Required
    public Integer value;

    @Column(nullable = false)
    public Date creationDate;

    @ManyToOne
    @Column(nullable = false)
    public User user;
    
    @ManyToOne
    @Column(nullable = false)
    public Route route;

    public Rating() {
        this.creationDate = new Date();
    }

    public Rating(User user, Route route, Integer value) {
        this.user = user;
        this.route = route;
        this.value = value;
        this.creationDate = new Date();
    }

    public static Finder<Long, Rating> find = new Finder<Long, Rating>(Long.class, Rating.class);

    public static Rating findByUserAndRoute(User user, Route route) {
        try  {
            return find.where().eq("user.id", user.id).eq("route.id", route.id).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }

}
*/