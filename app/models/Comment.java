package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment extends Model {

    @Id
    public Long id;

    @ManyToOne
    @Column(nullable = false)
    @Constraints.Required
    public User user;

    @Column(length = 1024, nullable = false)
    @Constraints.MaxLength(1024)
    @Constraints.Required
    public String value;

    @OneToOne
    public Photo photo;

    @Column(nullable = false)
    @Constraints.Required
    public Date creationDate;
    
    @ManyToOne
    public Route route;
    
    public Comment() {
        this.creationDate = new Date();
    }

    public Comment(User user, String value) {
        this.user = user;
        this.value = value;
        this.creationDate = new Date();
    }


    public static Finder<Long, Comment> find = new Finder<Long, Comment>(Long.class, Comment.class);
    
}
