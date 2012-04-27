package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Rating extends Model {

  @Id
  public Long id;

  @ManyToOne
  @Column(nullable = false)
  public User user;

  @Constraints.Min(1)
  @Constraints.Max(5)
  @Column(nullable = false)
  public Integer value;

  public Date creationDate;

  public Rating() {
    this.creationDate = new Date();
  }

  public Rating(User user, Integer value) {
    this.user = user;
    this.value = value;
    this.creationDate = new Date();
  }

}
