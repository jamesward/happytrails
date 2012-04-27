package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Entity
public class Route extends Model {

  @Id
  public Long id;

  @Column(nullable = false)
  public String name;

  @Column(nullable = false)
  public String description;

  public Double distanceInMiles;

  @ManyToOne
  public Region region;

  @Column(nullable = false)
  public String location;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Direction> directions;

  public URL mapUrl;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Rating> ratings;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Comment> comments;

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
