package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Region extends Model {

    @Id
    public Long id;

    @Column(length = 128, nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(128)
    public String name;

    public Region() {
    }

    public Region(String name) {
        this.name = name;
    }


    public static Finder<Long, Region> find = new Finder<Long, Region>(Long.class, Region.class);
    
}
