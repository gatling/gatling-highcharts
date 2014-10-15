import sbt._

object Dependencies {

  private val bundleArtifact = Artifact("gatling-bundle", "zip", "zip", "bundle")

  def charts(gatlingVersion: String)      = "io.gatling"     % "gatling-charts"   % gatlingVersion
  def app(gatlingVersion: String)         = "io.gatling"     % "gatling-app"      % gatlingVersion
  def recorder(gatlingVersion: String)    = "io.gatling"     % "gatling-recorder" % gatlingVersion
  def scalaCompiler(scalaVersion: String) = "org.scala-lang" % "scala-compiler"   % scalaVersion   % "runtime"
  def compiler(gatlingVersion: String)    = "io.gatling"     % "gatling-compiler" % gatlingVersion % "zinc"
  def bundle(gatlingVersion: String)      = "io.gatling"     % "gatling-bundle"   % gatlingVersion % "runtime" artifacts bundleArtifact

  def gatlingChartsHighchartsDeps(version: String) =
    Seq(charts _, app _, recorder _).map(_(version))

  def gatlingHighchartsBundleDeps(gatlingVersion: String, scalaVersion: String) =
    Seq(bundle _, compiler _).map(_(gatlingVersion)) :+ scalaCompiler(scalaVersion)
}
