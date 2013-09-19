import sbt._
import sbt.Keys._

import BuildSettings._
import Dependencies._
import Bundle._

object GatlingBuild extends Build {

	override lazy val settings = super.settings ++ {
		shellPrompt := { state => Project.extract(state).currentProject.id + " > " }
	}

	/******************/
	/** Root project **/
	/******************/

	lazy val root = Project("gatling-charts-highcharts", file("."))
		.settings(gatlingHighchartsSettings: _*)
		.settings(libraryDependencies ++= gatlingHighchartsDeps(version.value))
		.settings(bundleSettings: _*)

}