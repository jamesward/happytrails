package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment extends Model {

  @Id
  public Long id;

  @ManyToOne
  @Column(nullable = false)
  public User user;

  @Column(nullable = false)
  public String value;

  @OneToOne
  public Photo photo;

  public Date creationDate;

  public Comment(User user, String value) {
    this.user = user;
    this.value = value;
    this.creationDate = new Date();
  }
}
