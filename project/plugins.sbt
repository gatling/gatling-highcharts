resolvers += Resolver.bintrayIvyRepo("gatling", "sbt-plugins")

addSbtPlugin("io.gatling"       % "gatling-build-plugin" % "2.5.3")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager"  % "1.3.25")
addSbtPlugin("org.wartremover"  % "sbt-wartremover"      % "2.4.9")
addSbtPlugin("ch.epfl.scala"    % "sbt-scalafix"         % "0.9.16")
