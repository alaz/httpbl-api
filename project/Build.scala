import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {
  val appName         = "HttpBLApi"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "com.osinka.httpbl" %% "httpbl" % "1.0.0"
  )

  val main = play.Project(appName, appVersion, appDependencies)
}
