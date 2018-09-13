import io.gatling.build.license._
import io.gatling.build.ReleaseProcessKeys._
import io.gatling.build.MavenPublishKeys._

import sbt._
import sbt.Keys._
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport._

object BuildSettings {

  lazy val basicSettings = Seq(
    organization := "io.gatling.highcharts",
    githubPath := "gatling/gatling-highcharts",
    projectDevelopers := developers,
    headerLicense := GatlingHighChartsLicense,
    licenses := Seq("Gatling Highcharts" -> url("https://raw.githubusercontent.com/gatling/gatling-highcharts/master/LICENSE")),
    // [fl]
    //
    // [fl]
    useSonatypeRepositories := true,
    skipSnapshotDepsCheck := true
  )

  val developers = Seq(
    GatlingDeveloper("slandelle@gatling.io", "Stephane Landelle", isGatlingCorp = true),
    GatlingDeveloper("gcorre@gatling.io", "Guillaume Corré", isGatlingCorp = true)
  )

  lazy val gatlingHighchartsModuleSettings = basicSettings

  lazy val noArtifactToPublish =
    publishArtifact in Compile := false
}
