import sbt._
import sbt.Keys._
import sbt.classpath.ClasspathUtilities
import com.typesafe.sbt.SbtNativePackager._

object Bundle {

	val allJars = taskKey[Seq[File]]("List of all jars needed for the bundle")

	val bundleFile = taskKey[File]("Path of gatling-bundle")

	val unzippedBundleLocation = settingKey[File]("Directory where bundle is unzipped")

	val bundleArtifacts = {
		def bundleArtifact(ext: String) = Artifact("gatling-charts-highcharts", ext, ext, "bundle")

		Seq(
			addArtifact(bundleArtifact("zip"), packageBin in Universal)
		).flatMap(_.settings)
	}

	val bundleSettings = packagerSettings ++ bundleArtifacts ++ Seq(
		allJars := (fullClasspath in Runtime).value.map(_.data).filter(ClasspathUtilities.isArchive),
		mappings in Universal ++= allJars.value.map(jar => jar -> buildDestinationJarPath(jar, version.value)),
		mappings in Universal += {
			val highchartsJar = (packageBin in Compile).value
			(highchartsJar, "lib/" + highchartsJar.getName)
		},
		bundleFile := update.value.select(artifact = artifactFilter(classifier = "bundle")).head,
		unzippedBundleLocation := target.value / "unzipped",
		mappings in Universal ++= zipFileMappings.value
	) ++ useNativeZip

	def zipFileMappings = Def.task {
		IO.unzip(bundleFile.value, unzippedBundleLocation.value)
		val location = unzippedBundleLocation.value.listFiles.head
		// IO.unzip seems to "forget" file permissions, reset them after unzipping
		(location / "bin").***.get.map(_.setExecutable(true))
		val finder = (location.***) --- location
		finder x relativeTo(location)
	}

	def buildDestinationJarPath(sourceJarPath: File, version: String): String = {
		if(sourceJarPath.getName.startsWith("gatling") && !sourceJarPath.getName.contains(version))
			s"lib/${sourceJarPath.base}-$version.${sourceJarPath.ext}"
		else
			s"lib/${sourceJarPath.getName}"
	}
}
