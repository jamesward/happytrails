package controllers;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;
import models.*;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@With(CurrentUser.class)
public class RegionController extends Controller {

    // todo
    public static Result getRegionFeed(String urlFriendlyRegionName) throws FeedException, IOException {
        Region region = Region.findByUrlFriendlyName(urlFriendlyRegionName);

        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");

        feed.setTitle("Uber Tracks - " + region.getName());
        feed.setLink("http://hike.ubertracks.com");
        feed.setDescription("Updates for Hike Uber Tracks - " + region.getName());

        List entries = new ArrayList();
        
        for (Route route : region.routes) {
        
            SyndEntry entry = new SyndEntryImpl();
            entry.setTitle("Route - " + route.getName());
            entry.setLink("http://hike.ubertracks.com" + routes.RouteController.getRouteHtml(region.getUrlFriendlyName(), route.getUrlFriendlyName()).url());
            entry.setPublishedDate(route.creationDate);
            
            SyndContent description = new SyndContentImpl();
            description.setType("text/plain");

            // todo: templatize
            String text = "Route - " + route.getName()  + "\n\n" +
                    route.description + "\n\n" +
                    "Location: " + route.location + "\n\n" +
                    "Created: " + route.creationDate + "\n\n" +
                    "Distance (Miles): " + route.distanceInMiles + "\n\n" +
                    "Average Rating: " + route.getAverageRating() + "\n\n";
            
            for (Direction direction : route.directions) {
                text += direction.stepNumber + " - " + direction.instruction + "\n";
            }
            
            description.setValue(text);
            
            entry.setDescription(description);
            
            entries.add(entry);
        }
        feed.setEntries(entries);

        StringWriter writer = new StringWriter();
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, writer);
        writer.close();
        
        return ok(writer.toString()).as("application/rss+xml");
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
