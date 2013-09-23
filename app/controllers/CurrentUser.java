package controllers;

import models.User;
import play.cache.Cache;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.SimpleResult;

public class CurrentUser extends Action.Simple {

    public F.Promise<SimpleResult> call(Http.Context ctx) throws Throwable {
        String token = ctx.session().get("token");
        if (token != null) {
            User user = (User)Cache.get(token);
            if (user == null) {
                user = models.User.findByToken(token);
                Cache.set(token, user);
            }
            ctx.args.put("user", user);
        }
        return delegate.call(ctx);
    }

    public static User get() {
        return (User)Http.Context.current().args.get("user");
    }
}
