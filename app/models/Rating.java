package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Rating extends Model {

  @Id
  public Long id;

  @ManyToOne
  public User user;

  public Integer value;

  public Date creationDate;

}
