import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.recorder.ui.GatlingHttpProxyUI

import IDEPathHelper.{ requestBodiesFolder, recorderOutputFolder }

object Recorder extends App {

	GatlingHttpProxyUI.main(Array("-of", recorderOutputFolder, "-run", "-ide", "${package}", "-bf", requestBodiesFolder))
}