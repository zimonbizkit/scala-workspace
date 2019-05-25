name := "Animals"

organization in ThisBuild := "Edus"

version in ThisBuild := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.3"

resolvers += "Sonatype Releases Repository" at "https://oss.sonatype.org/content/repositories/Releases"

val liftVersion = "3.1.0"


//lazy val "animals" can be just equaled to 'project' as the project root is the same name as the
// variable name (animals). sbt (with scala or Java) would infer the name of the file automatically
lazy val animals = project in file("animals")
lazy val animalsRunner =
  (project in file("animals-runner"))
    .dependsOn(animals)
/*publishMavenStyle := true

pomExtra := <issueManagement><system>Github</system></issueManagement>

publishTo := Some(
  "Sonatype Releases Repository" at "https://oss.sonatype.org/content/repositories/releases"
)

credentials += Credentials(Path.userHome / ".ivy2" / "credentials")*/
