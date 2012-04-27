package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;

@Entity
public class Photo extends Model {

  @Id
  public Long id;

  public URL thumbnailUrl;

  public URL fullUrl;

}
