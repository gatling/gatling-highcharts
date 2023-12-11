import io.gatling.build.license._
import io.gatling.build.license.Apache2LicenseFilePlugin.Apache2LicenseFileKeys.gatlingApache2LicenseTask
import sbt._
import sbt.Keys._
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport._

object BuildSettings {
  lazy val basicSettings = Seq(
    organization := "io.gatling.highcharts",
    headerLicense := GatlingHighChartsLicense,
    licenses := Seq("Gatling Highcharts" -> url("https://raw.githubusercontent.com/gatling/gatling-highcharts/master/LICENSE")),
    // Avoid to write the Apache2 license
    Compile / gatlingApache2LicenseTask := Nil
  )
}
