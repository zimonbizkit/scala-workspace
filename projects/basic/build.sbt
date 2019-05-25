name := "Animals"

organization := "Edus"

version := "1.0"

scalaVersion := "2.12.3"

resolvers += "Sonatype Releases Repository" at "https://oss.sonatype.org/content/repositories/Releases"


  val liftVersion = "3.1.0"

  libraryDependencies ++= List(
    "net.liftweb" %% "lift-util" %  ,
    "net.liftweb" %% "lift-json" % liftVersion,
    "net.liftweb" %% "lift-webkit" % liftVersion,
    "org.scala-lang.modules" %% "scala-xml" % "1.0.6"

  )


publishMavenStyle := true

pomExtra := <issueManagement><system>Github</system></issueManagement>

publishTo := Some(
  "Sonatype Releases Repository" at "https://oss.sonatype.org/content/repositories/releases"
)

credentials += Credentials(Path.userHome / ".ivy2" / "credentials")