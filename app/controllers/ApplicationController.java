package controllers;

import models.Region;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;


@With(CurrentUser.class)
public class ApplicationController extends Controller {

    public static Result index() {
        return ok(views.html.index.render(Region.find.all()));
    }

    public static Result signupForm() {
        return ok(views.html.signupForm.render(form(User.class)));
    }

    public static Result signup() {
        Form<User> signupForm = form(User.class).bindFromRequest();
        if (signupForm.hasErrors()) {
            return badRequest(views.html.signupForm.render(signupForm));
        }
        else {
            User user = signupForm.get();
            user.save();
            
            return redirect(controllers.routes.ApplicationController.index());
        }
    }

    public static Result login() {
        Login login = form(Login.class).bindFromRequest().get();
        
        User user = User.findByEmailAddressAndPassword(login.emailAddress, login.password);

        // todo: redirect back to the page the user is already on for both cases
        if (user == null) {
            return badRequest("Invalid Login");
        }
        else {
            session("token", user.createToken());
            return redirect(controllers.routes.ApplicationController.index());
        }
    }
    
    public static class Login {
        
        public String emailAddress;

        public String password;
        
    }

}
