package plugins

import play.api.{Plugin, Application}
import org.beaucatcher.mongo.Context


object MongoDB {
  def context(implicit app: Application) = {
    app.plugin[MongoDBPlugin].getOrElse {
      throw new RuntimeException("MongoDBPlugin not registered")
    }.context
  }
}

class MongoDBPlugin(implicit val app: Application) extends Plugin {
  
  lazy val context = {
    Context(play.api.libs.concurrent.Akka.system)
  }
  
  override def onStart() {
    context
  }

  override def onStop() {
    context.close()
  }
  
}

/*
import play.api.{Plugin, Application}
import com.mongodb.{DBCollection, ServerAddress, MongoURI, Mongo}


// lifted from:
// https://github.com/vznet/play-mongo-jackson-mapper/blob/master/src/main/scala/play/modules/mongodb/jackson/MongoDB.scala
//

object MongoDB {
  
  def db(implicit app: Application) = {
    app.plugin[MongoDBPlugin].getOrElse {
      throw new RuntimeException("MongoDBPlugin not registered")
    }.db
  }

  def collection(name: String)(implicit app: Application): DBCollection = {
    db.createCollection(name, null)
    val collection = db.getCollection(name)
    collection
  }
  
}

class MongoDBPlugin(val app: Application) extends Plugin {

  lazy val (mongo, db) = {
    
    app.configuration.getString("mongodb.uri") match {
      case Some(uri) => {
        val mongoURI = new MongoURI(uri)
        val mongo = new Mongo(mongoURI)
        val db = mongo.getDB(mongoURI.getDatabase)
        
        if (mongoURI.getUsername != null) {
          if (!db.authenticate(mongoURI.getUsername, mongoURI.getPassword)) {
            throw new IllegalArgumentException("MongoDB authentication failed for user: " + mongoURI.getUsername + " on database: "
              + mongoURI.getDatabase);
          }
        }
        (mongo, db)
      }
      case None => {
        // Configure MongoDB
        // DB server string is comma separated, with optional port number after a colon
        val mongoDbServers = app.configuration.getString("mongodb.servers").getOrElse("localhost")
        // Parser for port number
        object Port {
          def unapply(s: String): Option[Int] = try {
            Some(s.toInt)
          } catch {
            case _: java.lang.NumberFormatException => None
          }
        }
        import scala.collection.JavaConversions._
        // Split servers
        val mongo = mongoDbServers.split(',') map {
          // Convert each server string to a ServerAddress, matching based on arguments
          _.split(':') match {
            case Array(host) => new ServerAddress(host)
            case Array(host, Port(port)) => new ServerAddress(host, port)
            case _ => throw new IllegalArgumentException("mongodb.servers must be a comma separated list of hostnames with" +
              " optional port numbers after a colon, eg 'host1.example.org:1111,host2.example.org'")
          }
        } match {
          case Array(single) => new Mongo(single)
          case multiple => new Mongo(multiple.toList)
        }

        // Load database
        val dbName = app.configuration.getString("mongodb.database").getOrElse("play")
        val db = mongo.getDB(dbName)

        // Authenticate if necessary
        val credentials = app.configuration.getString("mongodb.credentials")
        credentials.foreach {
          _.split(":", 2) match {
            case Array(username: String, password: String) => {
              if (!db.authenticate(username, password.toCharArray)) {
                throw new IllegalArgumentException("MongoDB authentication failed for user: " + username + " on database: "
                  + dbName);
              }
            }
            case _ => throw new IllegalArgumentException("mongodb.credentials must be a username and password separated by a colon")
          }
        }

        (mongo, db)
      }
    }
  }

  override def onStart() {
    mongo
  }

  override def onStop() {
    mongo.close()
  }

}
*/