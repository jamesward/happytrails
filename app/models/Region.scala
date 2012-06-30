package models

import org.beaucatcher.bson.ObjectId
import org.beaucatcher.bobject.{BObject, JsonMethods, CollectionAccessWithEntitiesBObjectOrCaseClassIdObjectId}
import org.beaucatcher.mongo.{BoundSyncCollection, Context}
import org.beaucatcher.caseclass.ClassAnalysis

case class Region(_id: ObjectId, name: String)

object Region extends CollectionAccessWithEntitiesBObjectOrCaseClassIdObjectId[Region] with JsonMethods[Region] {
  
  override def jsonSync(implicit context: Context): BoundSyncCollection[BObject, BObject, BObject, _, _] = sync[BObject]

  override val jsonAnalysis = new ClassAnalysis(classOf[Region])

  override def createQueryForAllObjects() = BObject() // get all regions
  
}