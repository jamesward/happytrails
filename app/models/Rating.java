package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Constraint;
import java.util.Date;

@Entity
public class Rating extends Model {

    @Id
    public Long id;

    @ManyToOne
    @Column(nullable = false)
    @Constraints.Required
    public User user;

    @Constraints.Min(1)
    @Constraints.Max(5)
    @Column(nullable = false)
    @Constraints.Required
    public Integer value;

    @Column(nullable = false)
    @Constraints.Required
    public Date creationDate;
    
    @ManyToOne
    public Route route;

    public Rating() {
        this.creationDate = new Date();
    }

    public Rating(User user, Integer value) {
        this.user = user;
        this.value = value;
        this.creationDate = new Date();
    }

}
