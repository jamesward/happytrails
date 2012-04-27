package models;

import org.h2.engine.User;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Rating extends Model {

  @Id
  public Long id;

  public User user;

  public Integer value;

  public Date creationDate;

}
