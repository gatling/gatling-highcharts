import sbt._
import sbt.Keys._

import BuildSettings._
import Dependencies._
import Bundle._

// Root project

lazy val root = Project("gatling-highcharts", file("."))
  .enablePlugins(AutomateHeaderPlugin, SonatypeReleasePlugin)
  .aggregate(gatlingChartsHighcharts, gatlingHighchartsBundle)
  .settings(basicSettings: _*)
  .settings(noArtifactToPublish)
  .settings(updateOptions := updateOptions.value.withGigahorse(false))

// Modules

def gatlingHighchartsModule(id: String) = Project(id, file(id))
  .enablePlugins(AutomateHeaderPlugin, SonatypeReleasePlugin)
  .settings(gatlingHighchartsModuleSettings: _*)

lazy val gatlingChartsHighcharts = gatlingHighchartsModule("gatling-charts-highcharts")
  .settings(libraryDependencies ++= gatlingChartsHighchartsDeps(version.value))
  .settings(exportJars := true)

lazy val gatlingHighchartsBundle = gatlingHighchartsModule("gatling-charts-highcharts-bundle")
  .dependsOn(gatlingChartsHighcharts)
  .enablePlugins(UniversalPlugin)
  .settings(libraryDependencies ++= gatlingHighchartsBundleDeps(version.value, scalaVersion.value))
  .settings(bundleSettings: _*)
  .settings(noArtifactToPublish)
