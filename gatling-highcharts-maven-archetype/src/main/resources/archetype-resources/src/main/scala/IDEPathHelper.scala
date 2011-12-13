import scala.tools.nsc.io.File
object IDEPathHelper {

	val packageName = "${package}"

	val url = getClass.getClassLoader.getResource("gatling.conf").getPath
	val projectDir = File(url).parents(2)

	val dataFolder = projectDir / "src/main/resources/data"
	val resultsFolder = projectDir / "target/gatling-results"
	val requestBodiesFolder = projectDir / "src/main/resources/request-bodies"
	val eclipseAssetsFolder = projectDir / "src/main/resources/assets"
	val eclipseSimulationFolder = projectDir / "src/main/scala" / packageName.replace(".", "/")
	val outputFolder = projectDir / "src/main/scala" / "com.excilys.ebi.gatling.toto".replace(".", "/")
}