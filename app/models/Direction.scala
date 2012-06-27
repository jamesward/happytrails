package models;

import play.api.Play.current
import net.vz.mongodb.jackson.{Id, ObjectId}
import org.codehaus.jackson.annotate.JsonProperty
import play.modules.mongodb.jackson.MongoDB
import reflect.BeanProperty


class Direction(@ObjectId @Id val id: String,
                @BeanProperty @JsonProperty("stepNumber") val stepNumber: Int,
                @BeanProperty @JsonProperty("instruction") val instruction: String,
                @BeanProperty @JsonProperty("route") val route: Route) {
  @ObjectId @Id def getId = id;
}

object Direction {
  private lazy val db = MongoDB.collection("directions", classOf[Direction], classOf[String])

  def create(direction: Direction) { db.save(direction) }
  def findAll() = { db.find().toArray }
}

/*
@Entity
public class Direction extends Model {

    @Id
    public Long id;

    @Column(nullable = false)
    @Constraints.Required
    public Integer stepNumber;

    @Column(length = 1024, nullable = false)
    @Constraints.MaxLength(1024)
    @Constraints.Required
    public String instruction;
    
    @ManyToOne
    public Route route;


    public Direction() {
    }

    public Direction(Route route, Integer stepNumber, String instruction) {
        this.route = route;
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }
}
*/