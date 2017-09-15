import sbt._
import sbt.Keys._
import sbt.classpath.ClasspathUtilities
import com.typesafe.sbt.SbtNativePackager._

object Bundle {

  val gatlingJars            = taskKey[Seq[File]]("List of all Gatling jars needed for the bundle")
  val bundleFile             = taskKey[File]("Path of gatling-bundle")
  val unzippedBundleLocation = settingKey[File]("Directory where bundle is unzipped")

  val bundleArtifacts = {
    def bundleArtifact(ext: String) = Artifact("gatling-charts-highcharts-bundle", ext, ext, "bundle")

    Seq(
      addArtifact(bundleArtifact("zip"), packageBin in Universal)
    ).flatMap(_.settings)
  }

  val bundleSettings = packagerSettings ++ bundleArtifacts ++ Seq(
    gatlingJars            := (fullClasspath in Runtime).value.map(_.data).filter(ClasspathUtilities.isArchive),
    mappings in Universal ++= gatlingJars.value.map(jar => jar -> buildDestinationJarPath("lib", jar, version.value)),
    bundleFile             := update.value.select(artifact = artifactFilter(classifier = "bundle")).head,
    unzippedBundleLocation := target.value / "unzipped",
    mappings in Universal ++= zipFileMappings.value
  ) ++ useNativeZip

  def zipFileMappings = Def.task {
    IO.unzip(bundleFile.value, unzippedBundleLocation.value)
    val location = unzippedBundleLocation.value.listFiles.head
    // IO.unzip seems to "forget" file permissions, reset them after unzipping
    (location / "bin").***.get.map(_.setExecutable(true))
    val finder = location.*** --- location
    finder pair relativeTo(location)
  }

  def buildDestinationJarPath(folder: String, sourceJarPath: File, version: String): String = {
    if(sourceJarPath.getName.startsWith("gatling") && !sourceJarPath.getName.contains(version))
      s"$folder/${sourceJarPath.base}-$version.${sourceJarPath.ext}"
    else
      s"$folder/${sourceJarPath.getName}"
  }
}
