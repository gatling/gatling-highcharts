import sbt._

import BuildSettings._
import Dependencies._

githubPath := "gatling/gatling-highcharts"
gatlingDevelopers := Seq(
  GatlingDeveloper("slandelle@gatling.io", "Stephane Landelle", isGatlingCorp = true),
  GatlingDeveloper("gcorre@gatling.io", "Guillaume Corré", isGatlingCorp = true)
)
scalaVersion := "2.13.12"
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

lazy val root = (project in file("."))
  .enablePlugins(GatlingOssPlugin)
  .settings(name := "gatling-charts-highcharts")
  .settings(basicSettings ++ CodeAnalysis.settings)
  .settings(libraryDependencies ++= gatlingChartsHighchartsDeps(version.value))
