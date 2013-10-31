import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtNativePackager._

object Bundle {

	val bundleFile = taskKey[File]("Path of gatling-bundle")
	val unzippedBundleLocation = settingKey[File]("Directory where bundle is unzipped")

	val bundleArtifacts = {
		def bundleArtifact(ext: String) = Artifact("gatling-charts-highcharts", ext, ext, "bundle")

		Seq(
			addArtifact(bundleArtifact("zip"), packageBin in Universal)
		).flatMap(_.settings)
	}

	val bundleSettings = packagerSettings ++ bundleArtifacts ++ Seq(
		mappings in Universal <+= (packageBin in Compile) map { jar => jar -> ("lib/" + jar.getName) },
		bundleFile := update.value.select(artifact = artifactFilter(classifier = "bundle")).head,
		unzippedBundleLocation := target.value / "unzipped",
		mappings in Universal ++= zipFileMappings.value
	)

	def zipFileMappings = Def.task {
		IO.unzip(bundleFile.value, unzippedBundleLocation.value)
		val location = unzippedBundleLocation.value.listFiles.head
		// IO.unzip seems to "forget" file permissions, reset them after unzipping
		(location / "bin").***.get.map(_.setExecutable(true))
		val finder = (location.***) --- location
		finder x relativeTo(location)
	}
}