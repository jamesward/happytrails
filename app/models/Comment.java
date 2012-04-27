package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment extends Model {

  @Id
  public Long id;

  @ManyToOne
  public User user;

  public String value;

  @OneToOne
  public Photo photo;

  public Date creationDate;

}
