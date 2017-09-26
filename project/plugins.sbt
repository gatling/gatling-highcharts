resolvers += Resolver.url("gatling",url("http://dl.bintray.com/content/gatling/sbt-plugins/"))(Resolver.ivyStylePatterns)

addSbtPlugin("io.gatling" % "gatling-build-plugin" % "2.0.7")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "0.8.0")

addMavenResolverPlugin
