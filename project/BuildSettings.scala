import sbt._
import sbt.Keys._
import io.gatling.build.MavenPublishPlugin.autoImport._

import scala.util.Properties.envOrNone

object BuildSettings {

  lazy val basicSettings = Seq(
    organization         := "io.gatling.highcharts",
    githubPath           := "gatling/gatling-highcharts",
    projectDevelopers    := developers,
    licenses             := Seq("Gatling Highcharts License" -> new URL("https://raw.github.com/gatling/gatling-highcharts/master/src/main/resources/META-INF/LICENSE")),
    resolvers            := envOrNone("CI").map(_ => Seq(Opts.resolver.sonatypeSnapshots)).getOrElse(Seq(Resolver.mavenLocal))
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
