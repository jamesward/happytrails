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

  public String name;

  public String description;

  public String distance;

  @ManyToOne
  public Region region;

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

}
