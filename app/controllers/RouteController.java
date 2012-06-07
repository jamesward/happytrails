package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@With(CurrentUser.class)
public class RouteController extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result saveRating(String urlFriendlyRegionName, String urlFriendlyRouteName) {
        User user = CurrentUser.get();
        Route route = getRoute(urlFriendlyRegionName, urlFriendlyRouteName);
        
        if ((route == null) || (user == null)) {
            return badRequest("User or Route not found");
        }

        Rating rating = form(Rating.class).bindFromRequest().get();
        if (rating != null) {
            Rating existingRating = Rating.findByUserAndRoute(user, route);
            if (existingRating != null) {
                existingRating.value = rating.value;
                existingRating.save();
            }
            else {
                rating.user = user;
                rating.route = route;
                rating.save();
            }
        }
        
        return redirect(routes.RouteController.getRouteHtml(urlFriendlyRegionName, urlFriendlyRouteName));
    }

    @Security.Authenticated(Secured.class)
    public static Result saveComment(String urlFriendlyRegionName, String urlFriendlyRouteName) {
        User user = CurrentUser.get();
        Route route = getRoute(urlFriendlyRegionName, urlFriendlyRouteName);

        if ((route == null) || (user == null)) {
            return badRequest("User or Route not found");
        }
        
        Comment comment = form(Comment.class).bindFromRequest().get();
        if (comment != null) {
            comment.user = user;
            comment.route = route;
            comment.save();
        }
        
        return redirect(routes.RouteController.getRouteHtml(urlFriendlyRegionName, urlFriendlyRouteName));
    }

    public static Result getRouteHtml(String urlFriendlyRegionName, String urlFriendlyRouteName) {
        Route route = getRoute(urlFriendlyRegionName, urlFriendlyRouteName);
        return ok(views.html.route.render(route));
    }
    
    private static Route getRoute(String urlFriendlyRegionName, String urlFriendlyRouteName) {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);
        if (region == null) {
            return null;
        }
        Route route = Route.findByUrlFriendlyName(region, urlFriendlyRouteName);
        return route;
    }
    
}
