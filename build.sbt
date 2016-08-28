name := "HttpBLApi"

version := "2.0-SNAPSHOT"

scalaVersion := "2.11.8"

scalacOptions := Seq("-feature", "-deprecation")

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    routesGenerator := InjectedRoutesGenerator
  )

libraryDependencies ++= Seq(
  "com.osinka.httpbl" %% "httpbl" % "2.0.0"
)
