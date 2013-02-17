import com.excilys.ebi.gatling.recorder.config.RecorderPropertiesBuilder
import com.excilys.ebi.gatling.recorder.controller.RecorderController

object Recorder extends App {

	val props = new RecorderPropertiesBuilder
	props.simulationOutputFolder(IDEPathHelper.recorderOutputDirectory.toString)
	props.simulationPackage("${package}")
	props.requestBodiesFolder(IDEPathHelper.requestBodiesDirectory.toString)

	RecorderController(props.build)
}