name := "testing"

version := "0.1"

scalaVersion := "2.12.3"

resolvers += "Sonatype Releases Repository" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.specs2" %% "specs2-core" % "4.0.2"
libraryDependencies += "org.specs2" %% "specs2-mock" % "4.0.2"
libraryDependencies += "org.specs2" %% "specs2-scalacheck" % "4.0.2"
//libraryDependencies += "org.mockito" %% "mockito-all" % "1.9.5"