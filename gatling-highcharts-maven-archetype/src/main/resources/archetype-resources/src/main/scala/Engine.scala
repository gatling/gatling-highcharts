import com.excilys.ebi.gatling.app.Gatling
import com.excilys.ebi.gatling.core.util.PathHelper.path2string

import IDEPathHelper.{simulationFolder, resultsFolder, requestBodiesFolder, packageName, dataFolder}

object Engine extends App {

	Gatling(dataFolder, resultsFolder, requestBodiesFolder, simulationFolder, packageName)
}