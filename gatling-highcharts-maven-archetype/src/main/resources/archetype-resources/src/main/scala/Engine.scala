import com.excilys.ebi.gatling.app.Gatling
import com.excilys.ebi.gatling.core.config.GatlingOptions
import com.excilys.ebi.gatling.core.util.PathHelper.path2string

object Engine extends App {

	new Gatling(GatlingOptions(
		dataDirectory = Some(IDEPathHelper.dataDirectory),
		resultsDirectory = Some(IDEPathHelper.resultsDirectory),
		requestBodiesDirectory = Some(IDEPathHelper.requestBodiesDirectory),
		simulationBinariesDirectory = Some(IDEPathHelper.mavenBinariesDirectory))).start
}