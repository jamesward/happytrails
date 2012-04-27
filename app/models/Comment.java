package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment extends Model {

  @Id
  public Long id;

  public User user;

  public String value;

  public Photo photo;

  public Date creationDate;

}
