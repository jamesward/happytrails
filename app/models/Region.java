package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Region extends Model {

  @Id
  public Long id;

  public String name;

  public Region() {
  }

  public Region(String name) {
    this.name = name;
  }
}
