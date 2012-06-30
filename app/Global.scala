import models.Region
import play.api._
import plugins.MongoDB
import play.api.Play.current
import utils.DemoData

object Global extends GlobalSettings {
  
  override def onStart(app: Application) {

    implicit val context = MongoDB.context
    
    if (app.mode.equals(Mode.Dev) && (Region.sync.find.size == 0)) {
      DemoData.loadDemoData()
    }
    
  }
  
}