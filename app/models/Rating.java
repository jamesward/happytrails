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

    @Constraints.Min(1)
    @Constraints.Max(5)
    @Column(nullable = false)
    @Constraints.Required
    public Integer value;

    @Column(nullable = false)
    public Date creationDate;

    @ManyToOne
    @Column(nullable = false)
    public User user;
    
    @ManyToOne
    @Column(nullable = false)
    public Route route;

    public Rating() {
        this.creationDate = new Date();
    }

    public Rating(User user, Integer value) {
        this.user = user;
        this.value = value;
        this.creationDate = new Date();
    }

    public static Finder<Long, Rating> find = new Finder<Long, Rating>(Long.class, Rating.class);

    public static Rating findByUserAndRoute(User user, Route route) {
        try  {
            return find.where().eq("user.id", user.id).eq("route.id", route.id).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }

}
