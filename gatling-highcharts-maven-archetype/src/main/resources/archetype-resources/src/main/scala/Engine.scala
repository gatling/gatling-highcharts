import com.excilys.ebi.gatling.app.Gatling
import com.excilys.ebi.gatling.core.util.PathHelper.path2string

import IDEPathHelper.{ resultsFolder, requestBodiesFolder, packageName, eclipseSimulationFolder, eclipseAssetsFolder, dataFolder }

object Engine extends App {

	Gatling(dataFolder, resultsFolder, requestBodiesFolder, eclipseAssetsFolder, eclipseSimulationFolder, packageName)
}