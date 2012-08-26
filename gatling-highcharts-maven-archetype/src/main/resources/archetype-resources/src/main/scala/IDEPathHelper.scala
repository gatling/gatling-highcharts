import scala.tools.nsc.io.File
import scala.tools.nsc.io.Path.string2path
object IDEPathHelper {

	val gatlingConfUrl = getClass.getClassLoader.getResource("gatling.conf").getPath
	val projectRootDir = File(gatlingConfUrl).parents(2)

	val mavenSourcesDirectory = (projectRootDir / "src" / "main" / "scala").toDirectory
	val mavenResourcesDirectory = (projectRootDir / "src" / "main" / "resources").toDirectory
	val mavenTargetDirectory = (projectRootDir / "target").toDirectory
	val mavenBinariesDirectory = (mavenTargetDirectory / "classes").toDirectory

	val dataDirectory = (mavenResourcesDirectory / "data").toDirectory
	val requestBodiesDirectory = (mavenResourcesDirectory / "request-bodies").toDirectory

	val recorderOutputDirectory = mavenSourcesDirectory
	val resultsDirectory = (mavenTargetDirectory / "gatling-results").toDirectory
}