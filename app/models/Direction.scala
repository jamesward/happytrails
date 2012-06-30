package models;


import org.beaucatcher.bson.ObjectId
import java.util.Date
import org.beaucatcher.bobject.{BObject, JsonMethods, CollectionAccessWithEntitiesBObjectOrCaseClassIdObjectId}
import org.beaucatcher.mongo.{BoundSyncCollection, Context}
import org.beaucatcher.caseclass.ClassAnalysis

case class Direction(_id: ObjectId, stepNumber: Int, instruction: String)

object Direction extends CollectionAccessWithEntitiesBObjectOrCaseClassIdObjectId[Direction] with JsonMethods[Direction] {

  override def jsonSync(implicit context: Context): BoundSyncCollection[BObject, BObject, BObject, _, _] = sync[BObject]

  override val jsonAnalysis = new ClassAnalysis(classOf[Direction])

  override def createQueryForAllObjects() = BObject()

}


/*
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
*/

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