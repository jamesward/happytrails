package models

import org.beaucatcher.bson.ObjectId
import java.util.Date
import org.beaucatcher.bobject.{BObject, JsonMethods, CollectionAccessWithEntitiesBObjectOrCaseClassIdObjectId}
import org.beaucatcher.mongo.{BoundSyncCollection, Context}
import org.beaucatcher.caseclass.ClassAnalysis


case class RegionSubscription(_id: ObjectId, lastSend: Date)

object RegionSubscription extends CollectionAccessWithEntitiesBObjectOrCaseClassIdObjectId[RegionSubscription] with JsonMethods[RegionSubscription] {

  override def jsonSync(implicit context: Context): BoundSyncCollection[BObject, BObject, BObject, _, _] = sync[BObject]

  override val jsonAnalysis = new ClassAnalysis(classOf[RegionSubscription])

  override def createQueryForAllObjects() = BObject()

}

/*
import play.api.Play.current
import java.util.Date
import reflect.BeanProperty
import org.codehaus.jackson.annotate.JsonProperty
import net.vz.mongodb.jackson.{Id, ObjectId}
import play.modules.mongodb.jackson.MongoDB
;

class RegionSubscription(@ObjectId @Id val id: String,
                         @BeanProperty @JsonProperty("user") val user: User,
                         @BeanProperty @JsonProperty("region") val region: Region,
                         @BeanProperty @JsonProperty("lastSend") val lastSend: Date) {
  @ObjectId @Id def getId = id;
}

object RegionSubscription {
  private lazy val db = MongoDB.collection("regionSubscriptions", classOf[RegionSubscription], classOf[String])

  def create(regionSubscription: RegionSubscription) { db.save(regionSubscription) }
  def findAll() = { db.find().toArray }
}


@Entity
public class RegionSubscription extends Model {
    
    @Id
    public Long id;
    
    @ManyToOne
    public User user;

    @ManyToOne
    public Region region;
    
    public Date lastSend;
    
    
    public RegionSubscription() {
        this.lastSend = new Date();
    }

    public RegionSubscription(User user, Region region) {
        this.user = user;
        this.region = region;
        this.lastSend = new Date();
    }

    public static Finder<Long, RegionSubscription> find = new Finder<Long, RegionSubscription>(Long.class, RegionSubscription.class);
    
    public static RegionSubscription findByUserAndRegion(User user, Region region) {
        return find.where().eq("user", user).eq("region", region).findUnique();
    }

}
*/
