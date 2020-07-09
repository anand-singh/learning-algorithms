import Dependencies._
import sbtrelease.ReleaseStateTransformations._
import sbtrelease.ReleasePlugin.autoImport._

ThisBuild / scalaVersion := "2.13.2"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "learning-algorithms",
    scalastyleFailOnError := true,
    scalastyleFailOnWarning := false,
    scalafmtOnCompile := true,
    libraryDependencies ++= Seq(catsCore, scalaTest % Test)
  )
  .settings(addCompilerPlugin(kindProjectorSetting))

scalacOptions ++= Seq(
  "-feature",
  "-unchecked",
  "-language:higherKinds",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-Xlint"
)

resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "Bintray ".at("https://dl.bintray.com/projectseptemberinc/maven")
)

releaseProcess := Seq(
  checkSnapshotDependencies,
  inquireVersions,
  // publishing locally in the process
  releaseStepCommandAndRemaining("+publishLocal"),
  releaseStepCommandAndRemaining("+clean"),
  releaseStepCommandAndRemaining("+test"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion,
  pushChanges
)

lazy val kindProjectorSetting = "org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full

addCommandAlias("fmt", ";scalafmtSbt;scalafmt;test:scalafmt")
addCommandAlias("cpl", ";compile;test:compile")
addCommandAlias("validate", ";clean;scalafmtSbtCheck;scalafmtCheck;test:scalafmtCheck;coverage;test;coverageOff;coverageReport;coverageAggregate")
addCommandAlias("testAll", ";clean;test;it:test")
