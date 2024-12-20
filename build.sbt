version in ThisBuild := "0.1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.8"

lazy val root = (project in file("."))
  .settings(
    name := "369FinalProj"
  )

val sparkVersion = "2.4.8"

resolvers ++= Seq(
  "apache-snapshots" at "https://repository.apache.org/snapshots/"

)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion
)