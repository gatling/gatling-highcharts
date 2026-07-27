import sbt.*

object Dependencies {
  // format: OFF
  private def app(gatlingVersion: String)         = "io.gatling"     % "gatling-app"         % gatlingVersion
  private def recorder(gatlingVersion: String)    = "io.gatling"     % "gatling-recorder"    % gatlingVersion
  // format: ON

  def gatlingChartsHighchartsDeps(version: String): Seq[ModuleID] =
    Seq(app, recorder).map(_(version))
}
