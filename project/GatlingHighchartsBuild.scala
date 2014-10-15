import sbt._
import sbt.Keys._

import BuildSettings._
import Dependencies._
import Bundle._

object GatlingHighchartsBuild extends Build {

  override lazy val settings = super.settings ++ {
    shellPrompt := { state => Project.extract(state).currentProject.id + " > " }
  }

  /******************/
  /** Root project **/
  /******************/

  def gatlingHighchartsModule(id: String) = Project(id, file(id)).settings(gatlingHighchartsModuleSettings: _*)

  lazy val root = Project("gatling-highcharts", file("."))
    .aggregate(gatlingChartsHighcharts, gatlingHighchartsBundle)
    .settings(basicSettings: _*)
    .settings(noCodeToPublish: _*)

  lazy val gatlingChartsHighcharts = gatlingHighchartsModule("gatling-charts-highcharts")
    .settings(libraryDependencies ++= gatlingChartsHighchartsDeps(version.value))
    .settings(exportJars := true)

  lazy val gatlingHighchartsBundle = gatlingHighchartsModule("gatling-charts-highcharts-bundle")
    .dependsOn(gatlingChartsHighcharts)
    .settings(libraryDependencies ++= gatlingHighchartsBundleDeps(version.value, scalaVersion.value))
    .settings(bundleSettings: _*)
    .settings(noCodeToPublish: _*)
}
