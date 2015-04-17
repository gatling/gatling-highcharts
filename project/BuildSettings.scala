import sbt._
import sbt.Keys._
import io.gatling.build.license._
import io.gatling.build.LicenseKeys._
import io.gatling.build.MavenPublishKeys._

object BuildSettings {

  lazy val basicSettings = Seq(
    organization            := "io.gatling.highcharts",
    githubPath              := "gatling/gatling-highcharts",
    projectDevelopers       := developers,
    license                 := HighCharts,
    useSonatypeRepositories := true
  ) ++ Release.settings

  val developers = Seq(
    GatlingDeveloper("slandelle@excilys.com", "Stephane Landelle", true),
    GatlingDeveloper("pdalpra@excilys.com", "Pierre Dal-Pra", true)
  )

  lazy val gatlingHighchartsModuleSettings = basicSettings

  lazy val noCodeToPublish = Seq(
    publishArtifact in Compile := false
  )
}
