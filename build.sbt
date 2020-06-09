import sbt._

import BuildSettings._
import Dependencies._
import Bundle._

// Root project

lazy val root = Project("gatling-highcharts", file("."))
  .enablePlugins(AutomateHeaderPlugin, SonatypeReleasePlugin)
  .aggregate(gatlingChartsHighcharts, gatlingHighchartsBundle)
  .settings(basicSettings: _*)
  .settings(noArtifactToPublish)

// Modules

def gatlingHighchartsModule(id: String) =
  Project(id, file(id))
    .enablePlugins(AutomateHeaderPlugin, SonatypeReleasePlugin)
    .settings(gatlingHighchartsModuleSettings ++ CodeAnalysis.settings)

lazy val gatlingChartsHighcharts = gatlingHighchartsModule("gatling-charts-highcharts")
  .settings(libraryDependencies ++= gatlingChartsHighchartsDeps(version.value))
  .settings(exportJars := true)

lazy val gatlingHighchartsBundle = gatlingHighchartsModule("gatling-charts-highcharts-bundle")
  .dependsOn(gatlingChartsHighcharts)
  .enablePlugins(UniversalPlugin)
  .settings(libraryDependencies ++= gatlingHighchartsBundleDeps(version.value, scalaVersion.value))
  .settings(bundleSettings: _*)
  .settings(noArtifactToPublish)

ThisBuild / scalafixDependencies += "com.nequissimus" %% "sort-imports" % "0.5.3"
