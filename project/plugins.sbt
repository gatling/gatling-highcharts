resolvers += Resolver.bintrayIvyRepo("gatling", "sbt-plugins")

addSbtPlugin("io.gatling"       % "gatling-build-plugin" % "2.6.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager"  % "1.8.1")
addSbtPlugin("org.wartremover"  % "sbt-wartremover"      % "2.4.13")
addSbtPlugin("ch.epfl.scala"    % "sbt-scalafix"         % "0.9.25")
