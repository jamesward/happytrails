import sbt._
import Keys._
import play.Project
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "happytrails"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    javaCore,
    javaJdbc,
    javaEbean,
    "org.webjars" % "webjars-play" % "2.1.0-1",
    "org.webjars" % "bootstrap" % "2.3.1",
    "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
    "rome" % "rome" % "1.0",
    "com.typesafe" %% "play-plugins-mailer" % "2.1.0",
    "com.amazonaws" % "aws-java-sdk" % "1.3.11",
    "org.imgscalr" % "imgscalr-lib" % "4.2"
  )

  val main = Project(appName, appVersion, appDependencies).settings(
  )

}
