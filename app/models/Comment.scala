package models;

import play.api.Play.current
import net.vz.mongodb.jackson.{Id, ObjectId}
import org.codehaus.jackson.annotate.JsonProperty
import play.modules.mongodb.jackson.MongoDB
import reflect.BeanProperty
import java.util.Date


class Comment(@ObjectId @Id val id: String,
              @BeanProperty @JsonProperty("value") val value: String,
              @BeanProperty @JsonProperty("creationDate") val creationDate: Date,
              @BeanProperty @JsonProperty("photo") val photo: S3Photo,
              @BeanProperty @JsonProperty("user") val user: User,
              @BeanProperty @JsonProperty("route") val route: Route) {
  @ObjectId @Id def getId = id;
}

object Comment {
  private lazy val db = MongoDB.collection("comments", classOf[Comment], classOf[String])

  def create(comment: Comment) { db.save(comment) }
  def findAll() = { db.find().toArray }
}

/*

@Entity
public class Comment extends Model {

    @Id
    public Long id;

    @Column(length = 1024, nullable = false)
    @Constraints.MaxLength(1024)
    @Constraints.Required
    public String value;

    @Column(nullable = false)
    public Date creationDate;

    @OneToOne
    public S3Photo photo;
    
    @ManyToOne
    @Column(nullable = false)
    public User user;
    
    @ManyToOne
    @Column(nullable = false)
    public Route route;
    
    public Comment() {
        this.creationDate = new Date();
    }

    public Comment(User user, Route route, String value) {
        this.user = user;
        this.route = route;
        this.value = value;
        this.creationDate = new Date();
    }


    public static Finder<Long, Comment> find = new Finder<Long, Comment>(Long.class, Comment.class);

    public static List<Comment> fiveMostRecent() {
        return find.orderBy("creationDate desc").fetch("route.region", new FetchConfig().query()).setMaxRows(5).findList();
    }
    
}

*/