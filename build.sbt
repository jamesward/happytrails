name := "happytrails"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.webjars" %% "webjars-play" % "2.2.0",
  "org.webjars" % "bootstrap" % "2.3.1",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "rome" % "rome" % "1.0",
  "com.typesafe" %% "play-plugins-mailer" % "2.1.0",
  "com.amazonaws" % "aws-java-sdk" % "1.3.11",
  "org.imgscalr" % "imgscalr-lib" % "4.2"
)

play.Project.playJavaSettings
