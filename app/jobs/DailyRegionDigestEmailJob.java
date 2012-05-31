package jobs;

import models.Comment;
import models.RegionSubscription;
import models.Route;
import models.User;
import play.Logger;
import play.api.Play;
import play.api.Mode;
import play.api.Application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DailyRegionDigestEmailJob {
    
    public static void main(String[] args) {

        Application application = new Application(new File(args[0]), DailyRegionDigestEmailJob.class.getClassLoader(), null, Mode.Prod());

        Play.start(application);
        
        List<RegionUserDigest> regionUserDigests = getRegionUserDigests();
        
        // todo: send emails
    }
    
    public static List<RegionUserDigest> getRegionUserDigests() {
        List<RegionUserDigest> regionUserDigests = new ArrayList<RegionUserDigest>();
        
        // todo: make parallel with Akka
        for (User user : User.find.all()) {
            
            RegionUserDigest regionUserDigest = new RegionUserDigest(user);
            
            for (RegionSubscription regionSubscription : user.regionSubscriptions) {
                for (Route route : regionSubscription.region.routes) {
                    // todo: can this be optimized with a db query
                    for (Comment comment : route.comments) {
                        if (comment.creationDate.after(regionSubscription.lastSend)) {
                            regionUserDigest.comments.add(comment);
                        }
                    }
                }
            }
            
            if (regionUserDigest.comments.size() > 0) {
                regionUserDigests.add(regionUserDigest);
            }
        }
        
        return regionUserDigests;
    }
    
    public static class RegionUserDigest {
        
        public User user;
        
        public List<Comment> comments = new ArrayList<Comment>();

        public RegionUserDigest(User user) {
            this.user = user;
        }
    }
    
}
