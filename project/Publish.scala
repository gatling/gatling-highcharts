import sbt._
import sbt.Keys._

import aether.Aether.aetherPublishSettings

object Publish {

  lazy val settings = aetherPublishSettings ++ Seq(
    crossPaths           := false,
    pomExtra             := scm ++ developersXml(developers),
    publishMavenStyle    := true,
    pomIncludeRepository := { _ => false },
    publishTo            := Some(if(isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging),
    credentials          += Credentials(Path.userHome / ".sbt" / ".credentials")
  )

  /************************/
  /** POM extra metadata **/
  /************************/

  private val scm = {
    <scm>
      <connection>scm:git:git@github.com:gatling/gatling-highcharts.git</connection>
      <developerConnection>scm:git:git@github.com:gatling/gatling-highcharts.git</developerConnection>
      <url>https://github.com/gatling/gatling-highcharts</url>
      <tag>HEAD</tag>
    </scm>
  }

  private case class GatlingDeveloper(emailAddress: String, name: String)

  private val developers = Seq(
    GatlingDeveloper("slandelle@excilys.com", "Stephane Landelle"),
    GatlingDeveloper("pdalpra@excilys.com", "Pierre Dal-Pra")
  )

  private def developersXml(devs: Seq[GatlingDeveloper]) = {
    <developers>
    {
      for(dev <- devs)
      yield {
        <developer>
          <id>{dev.emailAddress}</id>
          <name>{dev.name}</name>
          <organization>eBusiness Information, Excilys Group</organization>
        </developer>
      }
    }
    </developers>
  }
}
