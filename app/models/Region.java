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
    private String name;
    
    public void setName(String name) {
        this.name = name;
        this.urlFriendlyName = getUrlFriendlyName(name);
    }
    
    public String getName() {
        return name;
    }
    
    @Column(length = 128, nullable = false, unique = true)
    private String urlFriendlyName;
    
    public String getUrlFriendlyName() {
        return urlFriendlyName;
    }
    
    // converts the name to a URL friendly name
    // lowercase
    // consecutive whitespace becomes a single dash
    // all a-z, 0-9, and dashes are removed
    public static String getUrlFriendlyName(String name) {
        String urlFriendlyName = name.toLowerCase().replaceAll("\\s+", "-");
        urlFriendlyName = urlFriendlyName.replaceAll("[^a-z0-9\\-]","");
        return urlFriendlyName;
    }

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
