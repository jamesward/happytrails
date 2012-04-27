package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User extends Model {

  @Id
  public Long id;

  public String emailAddress;

  // todo: store as sha hash
  public String password;

  public String name;

  public Date creationDate;

}
