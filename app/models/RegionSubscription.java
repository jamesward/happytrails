package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class RegionSubscription extends Model {
    
    @Id
    public Long id;
    
    @ManyToOne
    public User user;

    @ManyToOne
    public Region region;
    
    public Date lastSend;
    
    
    public RegionSubscription() {
        this.lastSend = new Date();
    }

    public RegionSubscription(User user, Region region) {
        this.user = user;
        this.region = region;
        this.lastSend = new Date();
    }

    public static Finder<Long, RegionSubscription> find = new Finder<Long, RegionSubscription>(Long.class, RegionSubscription.class);
    
    public static RegionSubscription findByUserAndRegion(User user, Region region) {
        return find.where().eq("user", user).eq("region", region).findUnique();
    }

}
