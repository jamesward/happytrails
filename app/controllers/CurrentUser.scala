package controllers;

import models.User;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class CurrentUser extends Action.Simple {

    public Result call(Http.Context ctx) throws Throwable {
        String token = ctx.session().get("token");
        models.User user = models.User.findByToken(token);
        ctx.args.put("user", user);
        return delegate.call(ctx);
    }

    public static User get() {
        return (User)Http.Context.current().args.get("user");
    }
}
