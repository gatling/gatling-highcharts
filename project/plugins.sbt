resolvers += Resolver.bintrayIvyRepo("gatling", "sbt-plugins")

addSbtPlugin("io.gatling"       % "gatling-build-plugin" % "2.4.4")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager"  % "1.3.11")
addSbtPlugin("org.wartremover"  % "sbt-wartremover"      % "2.4.5")
addSbtPlugin("ch.epfl.scala"    % "sbt-scalafix"         % "0.9.15")
