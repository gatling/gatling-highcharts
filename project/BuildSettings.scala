import sbt._
import sbt.Keys._

import scala.util.Properties.envOrNone

import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import net.virtualvoid.sbt.graph.Plugin.graphSettings

object BuildSettings {

  lazy val basicSettings = Seq(
    homepage             := Some(new URL("http://gatling.io")),
    organization         := "io.gatling.highcharts",
    organizationHomepage := Some(new URL("http://gatling.io")),
    startYear            := Some(2011),
    licenses             := Seq("Gatling Highcharts License" -> new URL("https://raw.github.com/gatling/gatling-highcharts/master/src/main/resources/META-INF/LICENSE")),
    resolvers            := envOrNone("CI").map(_ => Seq(Opts.resolver.sonatypeSnapshots)).getOrElse(Seq(Resolver.mavenLocal)),
    updateOptions        := updateOptions.value.withLatestSnapshots(false).withConsolidatedResolution(true),
    scalaVersion         := "2.11.4",
    scalacOptions        := Seq(
      "-encoding",
      "UTF-8",
      "-target:jvm-1.7",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-language:implicitConversions",
      "-language:postfixOps"
    )
  ) ++ Publish.settings ++ Release.settings

  lazy val gatlingHighchartsModuleSettings =
    basicSettings ++ formattingSettings ++ graphSettings

  lazy val noCodeToPublish = Seq(
    publishArtifact in Compile := false
  )

  /*************************/
  /** Formatting settings **/
  /*************************/

  lazy val formattingSettings = SbtScalariform.scalariformSettings ++ Seq(
    ScalariformKeys.preferences := formattingPreferences
  )

  import scalariform.formatter.preferences._

  def formattingPreferences = 
    FormattingPreferences()
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(AlignParameters, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(IndentLocalDefs, true)
}
