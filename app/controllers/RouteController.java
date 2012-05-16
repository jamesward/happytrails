package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class RouteController extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result addRating(String region, String route) {
        return ok();
    }

    @Security.Authenticated(Secured.class)
    public static Result addComment(String region, String route) {
        return ok();
    }

    @Security.Authenticated(Secured.class)
    public static Result saveComment(String region, String route) {
        return ok();
    }
    
}
