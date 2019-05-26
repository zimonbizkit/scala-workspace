name := "testing_mockito"

version := "0.1"

scalaVersion := "2.12.8"

resolvers += "Sonatype Releases Repository" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.specs2" %% "specs2-core" % "4.0.2"
libraryDependencies += "org.mockito" % "mockito-all" % "1.9.5" % "test"