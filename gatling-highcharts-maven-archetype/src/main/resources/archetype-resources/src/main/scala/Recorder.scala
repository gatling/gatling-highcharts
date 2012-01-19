import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.recorder.ui.GatlingHttpProxyUI

import IDEPathHelper.{ requestBodiesFolder, outputFolder }

object Recorder extends App {

	GatlingHttpProxyUI.main(Array("-scala", "-of", outputFolder, "-run", "-ide", "${package}", "-bf", requestBodiesFolder))
}