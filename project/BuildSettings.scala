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
    scalaVersion         := "2.10.4",
    scalacOptions        := Seq(
      "-encoding",
      "UTF-8",
      "-target:jvm-1.6",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-language:implicitConversions",
      "-language:postfixOps"
    )
  )

  lazy val gatlingHighchartsSettings = 
    basicSettings ++ formattingSettings ++ graphSettings ++ 
    Publish.settings ++ Release.settings

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
