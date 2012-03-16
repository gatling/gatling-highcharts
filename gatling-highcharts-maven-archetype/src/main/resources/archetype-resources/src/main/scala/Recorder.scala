import scala.tools.nsc.io.Path.string2path
import scala.tools.nsc.io.File

import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.recorder.configuration.CommandLineOptionsConstants.{ REQUEST_BODIES_FOLDER_OPTION, PACKAGE_OPTION, OUTPUT_FOLDER_OPTION }
import com.excilys.ebi.gatling.recorder.ui.GatlingHttpProxyUI

import IDEPathHelper.{ requestBodiesFolder, recorderOutputFolder }

object Recorder extends App {

	GatlingHttpProxyUI.main(Array(OUTPUT_FOLDER_OPTION, recorderOutputFolder/ "${package}".replace(".", File.separator), PACKAGE_OPTION, "${package}", REQUEST_BODIES_FOLDER_OPTION, requestBodiesFolder))
}