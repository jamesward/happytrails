import models.User
import play.api.{Mode, Play, GlobalSettings, Application}
import utils.DemoData

object Global extends GlobalSettings {
  
  override def onStart(app: Application) {

    //S3Blob.initialize(application);

    if (app.mode.equals(Mode.Dev) && (User.findAll().size() == 0)) {
      DemoData.loadDemoData();
    }
    
  }
  
}