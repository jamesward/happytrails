package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import utils.UrlUtils;

import javax.persistence.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Double distanceInMiles;

    @ManyToOne
    public Region region;

    @Column(nullable = false)
    @Constraints.Required
    public String location;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Direction> directions = new ArrayList<Direction>();

    public URL mapUrl;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Rating> ratings  = new ArrayList<Rating>();

    @OneToMany(cascade = CascadeType.ALL)
    public List<Comment> comments = new ArrayList<Comment>();

    @OneToOne
    public Photo photo;

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

    // todo: Current setup means that route names must be globally unique.  They should only have to be unique to the region.
    public static Route findByUrlFriendlyName(String urlFriendlyName) {
        try  {
            return find.where().eq("urlFriendlyName", urlFriendlyName).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }
}
