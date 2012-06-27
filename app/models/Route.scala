package models;

import java.net.URL;
import java.util.Date;
import reflect.BeanProperty
import org.codehaus.jackson.annotate.JsonProperty
import net.vz.mongodb.jackson.{Id, ObjectId}
import play.modules.mongodb.jackson.MongoDB
;

class Route(@ObjectId @Id val id: String,
            @BeanProperty @JsonProperty("name") val name: String,
            @BeanProperty @JsonProperty("urlFriendlyName") val urlFriendlyName: String,
            @BeanProperty @JsonProperty("description") val description: String,
            @BeanProperty @JsonProperty("distanceInMiles") val distanceInMiles: Double,
            @BeanProperty @JsonProperty("location") val location: String,
            @BeanProperty @JsonProperty("region") val region: Region,
            @BeanProperty @JsonProperty("photo") val photo: S3Photo,
            @BeanProperty @JsonProperty("mapUrl") val mapUrl: URL,
            @BeanProperty @JsonProperty("creationDate") val creationDate: Date,
            @BeanProperty @JsonProperty("directions") val directions: List[Direction],
            @BeanProperty @JsonProperty("ratings") val ratings: List[Rating],
            @BeanProperty @JsonProperty("comments") val comments: List[Comment]) {
  @ObjectId @Id def getId = id;
}

object Route {
  private lazy val db = MongoDB.collection("routes", classOf[Route], classOf[String])

  def create(route: Route) { db.save(route) }
  def findAll() = { db.find().toArray }
}

/*
@Entity
public class Route extends Model {

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

    @Column(length = 128, nullable = false)
    private String urlFriendlyName;

    public String getUrlFriendlyName() {
        return urlFriendlyName;
    }

    @Column(nullable = false)
    @Constraints.Required
    public String description;

    @Column(nullable = false)
    @Constraints.Required
    public Double distanceInMiles;

    @Column(nullable = false)
    @Constraints.Required
    public String location;

    @ManyToOne
    public Region region;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    public List<Direction> directions = new ArrayList<Direction>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    public List<Rating> ratings  = new ArrayList<Rating>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    public List<Comment> comments = new ArrayList<Comment>();

    @OneToOne
    public S3Photo photo;

    public URL mapUrl;

    public Date creationDate;
    

    public Route() {
        this.creationDate = new Date();
    }

    public Route(String name, String description, Double distanceInMiles, Region region, String location) {
        setName(name);
        this.description = description;
        this.distanceInMiles = distanceInMiles;
        this.region = region;
        this.location = location;
        this.creationDate = new Date();
    }
    
    
    public Integer getAverageRating() {
        if (ratings.size() == 0) {
            return 0;
        }
        
        Integer ratingTotal = 0;
        
        for (Rating rating : ratings) {
            ratingTotal += rating.value;
        }
        
        return ratingTotal / ratings.size();
    }


    public static Finder<Long, Route> find = new Finder<Long, Route>(Long.class, Route.class);

    public static Route findByUrlFriendlyName(Region region, String urlFriendlyName) {
        try  {
            return find.fetch("comments", new FetchConfig().query()).fetch("comments.user", new FetchConfig().query()).where().eq("urlFriendlyName", urlFriendlyName).eq("region", region).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }

}

*/