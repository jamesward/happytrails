package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Comment extends Model {

    @Id
    public Long id;

    @Column(length = 1024, nullable = false)
    @Constraints.MaxLength(1024)
    @Constraints.Required
    public String value;

    @Column(nullable = false)
    public Date creationDate;

    @OneToOne
    public Photo photo;
    
    @ManyToOne
    @Column(nullable = false)
    public User user;
    
    @ManyToOne
    @Column(nullable = false)
    public Route route;
    
    public Comment() {
        this.creationDate = new Date();
    }

    public Comment(User user, Route route, String value) {
        this.user = user;
        this.route = route;
        this.value = value;
        this.creationDate = new Date();
    }


    public static Finder<Long, Comment> find = new Finder<Long, Comment>(Long.class, Comment.class);

    public static List<Comment> fiveMostRecent() {
        return find.orderBy("creationDate").setMaxRows(5).findList();
    }
    
}
