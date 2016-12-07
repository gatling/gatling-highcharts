import sbt._
import sbt.Keys._

import BuildSettings._
import Dependencies._
import Bundle._

import io.gatling.build.SonatypeReleasePlugin

// Root project

lazy val root = Project("gatling-highcharts", file("."))
  .enablePlugins(SonatypeReleasePlugin)
  .aggregate(gatlingChartsHighcharts, gatlingHighchartsBundle)
  .settings(basicSettings: _*)
  .settings(noArtifactToPublish)

// Modules

def gatlingHighchartsModule(id: String) = Project(id, file(id))
  .enablePlugins(SonatypeReleasePlugin)
  .settings(gatlingHighchartsModuleSettings: _*)

lazy val gatlingChartsHighcharts = gatlingHighchartsModule("gatling-charts-highcharts")
  .settings(libraryDependencies ++= gatlingChartsHighchartsDeps(version.value))
  .settings(exportJars := true)

lazy val gatlingHighchartsBundle = gatlingHighchartsModule("gatling-charts-highcharts-bundle")
  .dependsOn(gatlingChartsHighcharts)
  .settings(libraryDependencies ++= gatlingHighchartsBundleDeps(version.value, scalaVersion.value))
  .settings(bundleSettings: _*)
  .settings(noArtifactToPublish)
