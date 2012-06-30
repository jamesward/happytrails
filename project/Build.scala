import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "happytrails"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "com.github.twitter" % "bootstrap" % "2.0.4",
      "rome" % "rome" % "1.0",
      "org.mongodb" % "mongo-java-driver" % "2.8.0",
      "org.scala-lang" % "scalap" % "2.9.1",
      "com.typesafe" %% "play-plugins-mailer" % "2.0.2",
      "com.amazonaws" % "aws-java-sdk" % "1.3.11",
      "org.imgscalr" % "imgscalr-lib" % "4.2"
    )      
  
  //"org.beaucatcher" %% "beaucatcher-mongo" % "0.5.1-SNAPSHOT",
  //"org.beaucatcher" %% "beaucatcher-java-driver" % "0.5.1-SNAPSHOT",

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers += "webjars" at "http://webjars.github.com/m2"
    )

}
