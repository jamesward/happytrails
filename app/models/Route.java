package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Route extends Model {

    @Id
    public Long id;

    @Column(nullable = false)
    @Constraints.Required
    public String name;

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
        this.name = name;
        this.description = description;
        this.distanceInMiles = distanceInMiles;
        this.region = region;
        this.location = location;
        this.creationDate = new Date();
    }
}
