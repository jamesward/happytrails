import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "happytrails"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "com.github.twitter" % "bootstrap" % "2.0.4",
      "rome" % "rome" % "1.0",
      "com.mongodb.async" %% "hammersmith" % "0.2.9-1",
      "net.vz.mongodb.jackson" %% "play-mongo-jackson-mapper" % "1.0.0",
      "com.typesafe" %% "play-plugins-mailer" % "2.0.2",
      "com.amazonaws" % "aws-java-sdk" % "1.3.11",
      "org.imgscalr" % "imgscalr-lib" % "4.2"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers += "webjars" at "http://webjars.github.com/m2"
    )

}
