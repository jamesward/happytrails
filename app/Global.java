import models.User;
import play.Application;
import play.GlobalSettings;
import play.Play;
import utils.DemoData;
import utils.S3Blob;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {

        //Ebean.getServer(null).getAdminLogging().setDebugGeneratedSql(true);

        S3Blob.initialize(application);
        
        // load the demo data in dev mode if no other data exists
        if (Play.isDev() && (User.find.all().size() == 0)) {
            DemoData.loadDemoData();
        }

        super.onStart(application);
    }

}
