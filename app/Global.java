import models.User;
import play.Application;
import play.GlobalSettings;
import play.Play;
import play.mvc.Action;
import play.mvc.Http;

import java.lang.reflect.Method;

public class Global extends GlobalSettings {

  @Override
  public Action onRequest(Http.Request request, Method method) {

    if ((!Play.isProd()) && (User.find.all().size() == 0)) {
      DemoData.loadDemoData();
    }

    return super.onRequest(request, method);
  }
}
