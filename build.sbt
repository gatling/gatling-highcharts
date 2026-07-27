import sbt.*

import BuildSettings.*
import Dependencies.*

githubPath := "gatling/gatling-highcharts"
gatlingDevelopers := Seq(
  GatlingDeveloper("slandelle@gatling.io", "Stephane Landelle", isGatlingCorp = true),
  GatlingDeveloper("gcorre@gatling.io", "Guillaume Corré", isGatlingCorp = true)
)
scalaVersion := "2.13.18"

lazy val root = rootProject
  .enablePlugins(GatlingOssPlugin)
  .settings(name := "gatling-charts-highcharts")
  .settings(basicSettings ++ CodeAnalysis.settings)
  .settings(libraryDependencies ++= gatlingChartsHighchartsDeps(version.value))
