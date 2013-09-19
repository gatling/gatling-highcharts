import sbt._

object Dependencies {

	val excilysNexus = "Excilys Nexus" at "http://repository.excilys.com/content/groups/public"
	val excilysReleases = "Excilys Releases" at "http://repository.excilys.com/content/repositories/releases"
	val publicCloudbeesSnapshots = "Public Cloudbees Snapshots" at "http://repository-gatling.forge.cloudbees.com/snapshot/"
	val cloudbeesSnapshots = "Cloudbees Private Repository" at "davs://repository-gatling.forge.cloudbees.com/snapshot"

	val charts: String => ModuleID   = "io.gatling" % "gatling-charts"   % _
	val app: String => ModuleID      = "io.gatling" % "gatling-app"      % _
	val recorder: String => ModuleID = "io.gatling" % "gatling-recorder" % _

	val bundle: String => ModuleID   = "io.gatling" % "gatling-bundle"   % _ artifacts(Artifact("gatling-bundle", "zip", "zip", "bundle"))

	def gatlingHighchartsDeps(version: String) = Seq(charts, app, recorder, bundle).map(_(version))
}