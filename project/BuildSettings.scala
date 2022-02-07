import io.gatling.build.license._

import sbt._
import sbt.Keys._
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport._

object BuildSettings {

  lazy val basicSettings = Seq(
    organization := "io.gatling.highcharts",
    headerLicense := GatlingHighChartsLicense,
    licenses := Seq("Gatling Highcharts" -> url("https://raw.githubusercontent.com/gatling/gatling-highcharts/master/LICENSE"))
  )
}
