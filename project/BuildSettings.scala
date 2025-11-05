import io.gatling.build.license._
import sbt._
import sbt.Keys._
import sbtheader.HeaderPlugin.autoImport._

object BuildSettings {
  lazy val basicSettings = Seq(
    organization := "io.gatling.highcharts",
    headerLicense := GatlingHighChartsLicense,
    licenses := Seq("Gatling Highcharts" -> url("https://raw.githubusercontent.com/gatling/gatling-highcharts/master/LICENSE")),
    // Avoid to write the Apache2 license
    Compile / GatlingLicenseFileKeys.gatlingLicenseFileTask := Nil
  )
}
