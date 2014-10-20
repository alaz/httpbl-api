name := "HttpBLApi"

version := "2.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "com.osinka.httpbl" %% "httpbl" % "2.0.0-SNAPSHOT"
)
