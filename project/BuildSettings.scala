import sbt._
import sbt.Keys._

import aether.WagonWrapper
import aether.Aether._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import sbtrelease.ReleasePlugin._
import net.virtualvoid.sbt.graph.Plugin.graphSettings

import Dependencies._

object BuildSettings {

	lazy val basicSettings = Seq(
		homepage              := Some(new URL("http://gatling.io")),
		organization          := "io.gatling.highcharts",
		organizationHomepage  := Some(new URL("http://gatling.io")),
		startYear             := Some(2011),
		licenses              := Seq("Gatling Highcharts License" -> new URL("https://raw.github.com/excilys/gatling-highcharts/master/src/main/resources/META-INF/LICENCE")),
		resolvers             := Seq(Resolver.mavenLocal, excilysNexus, publicCloudbeesSnapshots),
		scalaVersion          := "2.10.2",
		crossPaths            := false,
		pomExtra              := scm ++ developersXml(developers),
		scalacOptions         := Seq(
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
		basicSettings ++ formattingSettings ++ graphSettings ++ publishingSettings ++ releaseSettings

	lazy val publishingSettings = 
		aetherSettings ++ aetherPublishSettings ++ aetherPublishLocalSettings ++ Seq(
			pomIncludeRepository := { _ => false },
			wagons := {
				if(isSnapshot.value) 
					Seq(WagonWrapper("davs", "org.apache.maven.wagon.providers.webdav.WebDavWagon"))
				else Seq.empty
			},
			publishTo := { if(isSnapshot.value) Some(cloudbeesSnapshots) else Some(excilysReleases) },
			credentials += { if(isSnapshot.value) Credentials(file("/private/gatling/.credentials")) else Credentials(Path.userHome / ".sbt" / ".credentials") }
			)

	/************************/
	/** POM extra metadata **/
	/************************/

	private val scm = {
		<scm>
			<connection>scm:git:git@github.com:excilys/gatling-highcharts.git</connection>
			<developerConnection>scm:git:git@github.com:excilys/gatling-highcharts.git</developerConnection>
			<url>https://github.com/excilys/gatling-highcharts</url>
			<tag>HEAD</tag>
		</scm>
	}

	case class GatlingDeveloper(emailAddress: String, name: String, isEbiz: Boolean)

	val developers = Seq(
		GatlingDeveloper("slandelle@excilys.com", "Stephane Landelle", true),
		GatlingDeveloper("rsertelon@excilys.com", "Romain Sertelon", true)
	)

	private def developersXml(devs: Seq[GatlingDeveloper]) = {
		<developers>
		{
			for(dev <- devs)
			yield {
				<developer>
					<id>{dev.emailAddress}</id>
					<name>{dev.name}</name>
					{ if (dev.isEbiz) <organization>eBusiness Information, Excilys Group</organization> }
				</developer>
			}
		}
		</developers>
	}

	/*************************/
	/** Formatting settings **/
	/*************************/

	lazy val formattingSettings = SbtScalariform.scalariformSettings ++ Seq(
		ScalariformKeys.preferences := formattingPreferences
	)

	import scalariform.formatter.preferences._

	def formattingPreferences = 
		FormattingPreferences()
			.setPreference(DoubleIndentClassDeclaration, false)
			.setPreference(IndentWithTabs, true)
}