import io.gatling.build.license.*
import sbt.{ *, given }
import sbt.Keys.*
import sbtheader.HeaderPlugin.autoImport.*

object BuildSettings {
  lazy val basicSettings: Seq[Def.Setting[?]] = Seq(
    organization := "io.gatling.highcharts",
    headerLicense := GatlingHighChartsLicense,
    licenses := Seq("Gatling Highcharts" -> uri("https://raw.githubusercontent.com/gatling/gatling-highcharts/master/LICENSE")),
    // Avoid to write the Apache2 license
    Compile / GatlingLicenseFileKeys.gatlingLicenseFileTask := Def.uncached(Nil)
  )
}
