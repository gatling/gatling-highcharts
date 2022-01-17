import sbt._

import BuildSettings._
import Dependencies._
import Bundle._

Global / githubPath := "gatling/gatling-highcharts"
Global / gatlingDevelopers := Seq(
  GatlingDeveloper("slandelle@gatling.io", "Stephane Landelle", isGatlingCorp = true),
  GatlingDeveloper("gcorre@gatling.io", "Guillaume Corré", isGatlingCorp = true)
)
Global / scalaVersion := "2.13.8"
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

// Root project

lazy val root = Project("gatling-highcharts", file("."))
  .enablePlugins(GatlingOssPlugin)
  .aggregate(gatlingChartsHighcharts, gatlingHighchartsBundle)
  .settings(basicSettings)
  .settings(publish / skip := true)

// Modules

def gatlingHighchartsModule(id: String) =
  Project(id, file(id))
    .enablePlugins(GatlingOssPlugin)
    .settings(basicSettings ++ CodeAnalysis.settings)

lazy val gatlingChartsHighcharts = gatlingHighchartsModule("gatling-charts-highcharts")
  .settings(libraryDependencies ++= gatlingChartsHighchartsDeps(version.value))
  .settings(exportJars := true)

lazy val gatlingHighchartsBundle = gatlingHighchartsModule("gatling-charts-highcharts-bundle")
  .dependsOn(gatlingChartsHighcharts)
  .enablePlugins(UniversalPlugin)
  .settings(libraryDependencies ++= gatlingHighchartsBundleDeps(version.value, scalaVersion.value))
  .settings(bundleSettings: _*)
  .settings(packageDoc / publishArtifact := false) // no javadoc
  .settings(packageSrc / publishArtifact := false) // no source
  .settings(packageBin / publishArtifact := false) // no jar (only the bundle.zip remains)
