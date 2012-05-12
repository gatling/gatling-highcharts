import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.recorder.GatlingRecorder
import com.excilys.ebi.gatling.recorder.GatlingRecorder._

import IDEPathHelper.{ requestBodiesFolder, recorderOutputFolder }

object Recorder extends App {
	GatlingRecorder.main(Array(OUTPUT_FOLDER_OPTION, recorderOutputFolder, PACKAGE_OPTION, "${package}", REQUEST_BODIES_FOLDER_OPTION, requestBodiesFolder))
}