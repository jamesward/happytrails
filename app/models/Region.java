package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import utils.UrlUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Region extends Model {

    @Id
    public Long id;

    @Column(length = 128, nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(128)
    private String name;
    
    public void setName(String name) {
        this.name = name;
        this.urlFriendlyName = UrlUtils.getUrlFriendlyName(name);
    }
    
    public String getName() {
        return name;
    }
    
    @Column(length = 128, nullable = false, unique = true)
    private String urlFriendlyName;
    
    public String getUrlFriendlyName() {
        return urlFriendlyName;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Route> routes = new ArrayList<Route>();
    
    
    @OneToMany(cascade = CascadeType.ALL)
    public List<RegionSubscription> regionSubscriptions = new ArrayList<RegionSubscription>();

    
    public Region() {
    }

    public Region(String name) {
        setName(name);
    }


    public static Finder<Long, Region> find = new Finder<Long, Region>(Long.class, Region.class);

    public static Region findByUrlFriendlyName(String urlFriendlyName) {
        try  {
            return find.where().eq("urlFriendlyName", urlFriendlyName).findUnique();
        }
        catch (Exception e) {
            return null;
        }
    }
    
}
