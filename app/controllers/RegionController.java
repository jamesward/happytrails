package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;


public class RegionController extends Controller {

    public static Result getRegionFeed(String region) {
        return ok();
    }

    @Security.Authenticated(Secured.class)
    public static Result subscribe(String region) {
        return ok();
    }

    @Security.Authenticated(Secured.class)
    public static Result addRoute(String region) {
        return ok();
    }

    @Security.Authenticated(Secured.class)
    public static Result saveRoute(String region) {
        return ok();
    }

    public static Result getRegionHtml(String region) {
        return ok();
    }
    
}
