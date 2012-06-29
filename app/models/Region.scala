package models;

import reflect.BeanProperty
import org.codehaus.jackson.annotate.JsonProperty
import net.vz.mongodb.jackson.{Id, ObjectId}
import play.modules.mongodb.jackson.MongoDB
import play.api.Play.current

class Region(@ObjectId @Id val id: String,
             @BeanProperty @JsonProperty("name") val name: String,
             @BeanProperty @JsonProperty("urlFriendlyName") val urlFriendlyName: String,
             @BeanProperty @JsonProperty("routes") val routes: List[Route],
             @BeanProperty @JsonProperty("regionSubscriptions") val regionSubscriptions: List[RegionSubscription],
             @BeanProperty @JsonProperty("photo") val photo: S3Photo) {
  @ObjectId @Id def getId = id;
}

object Region {
  private lazy val db = MongoDB.collection("regions", classOf[Region], classOf[String])

  def create(region: Region) { db.save(region) }
  def findAll() = { db.find().toArray }
}
/*

@Entity
public class Region extends Model {

    @Id
    public Long id;

    @Column(length = 128, nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(128)
    private String name;
    
    public void setName(String name) {
        this.name = name;
        this.urlFriendlyName = UrlUtils.getUrlFriendlyName(name);
    }
    
    public String getName() {
        return name;
    }
    
    @Column(length = 128, nullable = false, unique = true)
    private String urlFriendlyName;
    
    public String getUrlFriendlyName() {
        return urlFriendlyName;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    public List<Route> routes = new ArrayList<Route>();
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    public List<RegionSubscription> regionSubscriptions = new ArrayList<RegionSubscription>();

    @OneToOne(cascade = CascadeType.ALL)
    public S3Photo photo;
    
    public Region() {
    }

    public Region(String name) {
        setName(name);
    }


    public static Finder<Long, Region> find = new Finder<Long, Region>(Long.class, Region.class);

    public static Region findByUrlFriendlyName(String urlFriendlyName) {
        try  {
            return find.where().eq("urlFriendlyName", urlFriendlyName).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }

}
*/