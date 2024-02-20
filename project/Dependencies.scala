import sbt._

object Dependencies {
  // format: OFF
  def app(gatlingVersion: String)         = "io.gatling"     % "gatling-app"         % gatlingVersion
  def recorder(gatlingVersion: String)    = "io.gatling"     % "gatling-recorder"    % gatlingVersion
  // format: ON

  def gatlingChartsHighchartsDeps(version: String) =
    Seq(app _, recorder _).map(_(version))
}
