resolvers += Resolver.bintrayIvyRepo("gatling", "sbt-plugins")

addSbtPlugin("io.gatling"       % "gatling-build-plugin" % "2.6.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager"  % "1.7.4")
addSbtPlugin("org.wartremover"  % "sbt-wartremover"      % "2.4.10")
addSbtPlugin("ch.epfl.scala"    % "sbt-scalafix"         % "0.9.18-1")
