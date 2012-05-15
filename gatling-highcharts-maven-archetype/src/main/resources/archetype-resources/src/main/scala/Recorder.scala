import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.recorder.config.Options
import com.excilys.ebi.gatling.recorder.controller.RecorderController

import IDEPathHelper.{ requestBodiesFolder, recorderOutputFolder }

object Recorder extends App {
	
		RecorderController(Options(
		outputFolder = Some(recorderOutputFolder),
		simulationPackage = Some("${package}"),
		requestBodiesFolder = Some(requestBodiesFolder)))
}