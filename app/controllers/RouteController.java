package controllers;

import models.Route;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@With(CurrentUser.class)
public class RouteController extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result saveRating(String region, String route) {
        return ok();
    }

    @Security.Authenticated(Secured.class)
    public static Result saveComment(String region, String route) {
        return ok();
    }

    public static Result getRouteHtml(String urlFriendlyRegionName, String urlFriendlyRouteName) {
        Route route = Route.findByUrlFriendlyName(urlFriendlyRouteName);
        return ok(views.html.route.render(route));
    }
    
}
