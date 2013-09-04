import sbt._

object Dependencies {

	val excilysNexus = "Excilys Nexus" at "http://repository.excilys.com/content/groups/public"
	val excilysReleases = "Excilys Releases" at "http://repository.excilys.com/content/repositories/releases"
	val publicCloudbeesSnapshots = "Public Cloudbees Snapshots" at "http://repository-gatling.forge.cloudbees.com/snapshot/"
	val cloudbeesSnapshots = "Cloudbees Private Repository" at "davs://repository-gatling.forge.cloudbees.com/snapshot"

	val charts   = "io.gatling" % "gatling-charts"   % "2.0.0-SNAPSHOT"
	val app      = "io.gatling" % "gatling-app"      % "2.0.0-SNAPSHOT"
	val recorder = "io.gatling" % "gatling-recorder" % "2.0.0-SNAPSHOT"

	val bundle   = "io.gatling" % "gatling-bundle"   % "2.0.0-SNAPSHOT" artifacts(Artifact("gatling-bundle", "zip", "zip", "bundle"))

	val gatlingHighchartsDeps = Seq(charts, app, recorder, bundle)
}