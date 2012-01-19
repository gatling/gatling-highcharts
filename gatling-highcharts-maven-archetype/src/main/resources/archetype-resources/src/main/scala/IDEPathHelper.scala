import scala.tools.nsc.io.File
object IDEPathHelper {

	val packageName = "${package}"

	val gatlingConfUrl = getClass.getClassLoader.getResource("gatling.conf").getPath
	val projectRootDir = File(gatlingConfUrl).parents(2)

	val resourcesDirPath = Path(List("src", "main", "resources"))
	val scalaSourcesDirPath = Path(List("src", "main", "scala"))
	val packageAsPath: Path = packageName.replace(".", java.io.File.separator)

	val dataFolder = projectRootDir / resourcesDirPath / "data"
	val resultsFolder = projectRootDir / Path(List("target", "gatling-results"))
	val requestBodiesFolder = projectRootDir / resourcesDirPath / "request-bodies"
	val simulationFolder = projectRootDir / scalaSourcesDirPath / packageAsPath
	val outputFolder = projectRootDir / scalaSourcesDirPath / packageAsPath
}