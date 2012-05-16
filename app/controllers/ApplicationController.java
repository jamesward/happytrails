package controllers;

import core.Login;
import models.Region;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class ApplicationController extends Controller {

    public static Result index() {
        return ok(views.html.index.render(Region.find.all()));
    }

    public static Result signupForm() {
        return ok(views.html.signup.render(form(User.class)));
    }

    public static Result signup() {
        Form<User> signupForm = form(User.class).bindFromRequest();
        if (signupForm.hasErrors()) {
            return badRequest(views.html.signup.render(signupForm));
        }
        else {
            User user = signupForm.get();
            user.save();
            
            return ok(views.html.signupComplete.render(user));
        }
    }

    public static Result login() {
        Login loginForm = form(Login.class).bindFromRequest().get();
        
        User user = User.findByEmailAddressAndPassword(loginForm.emailAddress, loginForm.password);
        
        if (user == null) {
            return unauthorized();
        }
        else {
            return redirect(controllers.routes.ApplicationController.index());
        }
        
    }

}
