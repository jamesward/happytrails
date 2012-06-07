import com.avaje.ebean.Ebean;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.Play;
import utils.DemoData;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {

        //Ebean.getServer(null).getAdminLogging().setDebugGeneratedSql(true);
        
        // load the demo data in dev mode if no other data exists
        if (Play.isDev() && (User.find.all().size() == 0)) {
            DemoData.loadDemoData();
        }

        super.onStart(application);
    }

}
