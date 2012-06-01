package controllers;

import models.Region;
import models.RegionSubscription;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@With(CurrentUser.class)
public class RegionController extends Controller {

    public static Result getRegionFeed(String urlFriendlyRegionName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        return ok(region.getName());
    }

    @Security.Authenticated(Secured.class)
    public static Result subscribe(String urlFriendlyRegionName) {
        User user = User.findByToken(session().get("token"));
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        
        RegionSubscription regionSubscription = new RegionSubscription(user, region);
        regionSubscription.save();
        
        return redirect(routes.RegionController.getRegionHtml(urlFriendlyRegionName));
    }

    @Security.Authenticated(Secured.class)
    public static Result addRoute(String urlFriendlyRegionName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        return ok(region.getName());
    }

    @Security.Authenticated(Secured.class)
    public static Result saveRoute(String urlFriendlyRegionName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        return ok(region.getName());
    }

    public static Result getRegionHtml(String urlFriendlyRegionName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        return ok(views.html.region.render(region));
    }
    
}
