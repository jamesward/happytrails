package controllers;

import models.*;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

@With(CurrentUser.class)
public class RouteController extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result saveRating(String urlFriendlyRegionName, String urlFriendlyRouteName, Integer rating) {
        User user = CurrentUser.get();
        Route route = getRoute(urlFriendlyRegionName, urlFriendlyRouteName);
        
        if ((route == null) || (user == null)) {
            return badRequest("User or Route not found");
        }

        if (rating != null) {
            Rating existingRating = Rating.findByUserAndRoute(user, route);
            if (existingRating != null) {
                existingRating.value = rating;
                existingRating.save();
            }
            else {
                Rating newRating = new Rating(user, route, rating);
                newRating.save();
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

        Form<Comment> commentForm = Form.form(Comment.class).bindFromRequest();
        
        if (commentForm.hasErrors()) {
            flash("error", "Comment must not be empty.");
            return redirect(routes.RouteController.getRouteHtml(urlFriendlyRegionName, urlFriendlyRouteName));
        }
        
        Comment comment = Form.form(Comment.class).bindFromRequest().get();
        if (comment != null) {
            comment.user = user;
            comment.route = route;
            comment.save();
            Cache.remove("five-most-recent-comments");
        }
        
        return redirect(routes.RouteController.getRouteHtml(urlFriendlyRegionName, urlFriendlyRouteName));
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteRoute(String urlFriendlyRegionName, String urlFriendlyRouteName) {
        User user = CurrentUser.get();
        Route route = getRoute(urlFriendlyRegionName, urlFriendlyRouteName);

        if ((route == null) || (user == null)) {
            return badRequest("User or Route not found");
        }
        
        if (!user.isAdmin) {
            return unauthorized("You aren't allowed to do this.");
        }
        
        route.delete();
        Cache.remove("five-most-recent-comments");

        return redirect(routes.RegionController.getRegionHtml(urlFriendlyRegionName, "name"));
    }

    public static Result getRouteHtml(String urlFriendlyRegionName, String urlFriendlyRouteName) {
        Route route = getRoute(urlFriendlyRegionName, urlFriendlyRouteName);
        
        if (route == null) {
            return notFound("Region not found");
        }
        
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
