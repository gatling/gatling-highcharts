import sbt._

object Dependencies {

  val charts:   String => ModuleID = "io.gatling" % "gatling-charts"   % _
  val app:      String => ModuleID = "io.gatling" % "gatling-app"      % _
  val recorder: String => ModuleID = "io.gatling" % "gatling-recorder" % _

  val bundle: String => ModuleID   = "io.gatling" % "gatling-bundle"   % _ artifacts Artifact("gatling-bundle", "zip", "zip", "bundle")

  def gatlingHighchartsDeps(version: String) = Seq(charts, app, recorder, bundle).map(_(version))
}
