package controllers;

import models.Region;
import models.RegionSubscription;
import models.Route;
import models.User;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

import java.util.ArrayList;
import java.util.List;

@With(CurrentUser.class)
public class RegionController extends Controller {

    // todo
    public static Result getRegionFeed(String urlFriendlyRegionName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        return ok(region.getName());
    }

    @Security.Authenticated(Secured.class)
    public static Result subscribe(String urlFriendlyRegionName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        User user = CurrentUser.get();
        
        if ((region != null) && (user != null)) {
            RegionSubscription regionSubscription = new RegionSubscription(user, region);
            regionSubscription.save();
            return redirect(routes.RegionController.getRegionHtml(urlFriendlyRegionName));
        }
        else {
            return badRequest("Could not find region or user");
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result addRoute(String urlFriendlyRegionName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        return ok(views.html.routeForm.render(region, form(Route.class)));
    }

    @Security.Authenticated(Secured.class)
    public static Result saveRoute(String urlFriendlyRegionName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        Form<Route> routeForm = form(Route.class).bindFromRequest();
        
        // check if the name is a duplicate
        if (Route.findByUrlFriendlyName(region, routeForm.get().getUrlFriendlyName()) != null) {
            List<ValidationError> errors = new ArrayList<ValidationError>();
            errors.add(new ValidationError("", "Duplicate Route Name", new ArrayList()));
            routeForm.errors().put("error", errors);
        }
        
        if (routeForm.hasErrors()) {
            return badRequest(views.html.routeForm.render(region, routeForm));
        }
        else {
            Route route = routeForm.get();
            route.region = region;
            route.save();
            return redirect(routes.RouteController.getRouteHtml(region.getUrlFriendlyName(), route.getUrlFriendlyName()));
        }
    }

    public static Result getRegionHtml(String urlFriendlyRegionName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        if (region == null) {
            return badRequest("Could not find that region");
        }
        else {
            return ok(views.html.region.render(region));
        }
    }
    
}
