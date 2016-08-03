import com.typesafe.sbt.pgp.PgpKeys
import sbt._
import sbt.Keys._

import scala.languageFeature.experimental
import scala.languageFeature.experimental.macros

object Build extends Build {

  val org = "com.sksamuel.scalafunc"

  val ScalaVersion = "2.11.8"
  val ScalatestVersion = "2.2.4"
  val Slf4jVersion = "1.7.12"
  val Log4jVersion = "1.2.17"

  val rootSettings = Seq(
    organization := org,
    scalaVersion := ScalaVersion,
    crossScalaVersions := Seq(ScalaVersion, "2.10.6"),
    publishMavenStyle := true,
    resolvers += Resolver.mavenLocal,
    publishArtifact in Test := false,
    parallelExecution in Test := false,
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8"),
    javacOptions := Seq("-source", "1.8", "-target", "1.8"),
    sbtrelease.ReleasePlugin.autoImport.releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    sbtrelease.ReleasePlugin.autoImport.releaseCrossBuild := true,
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.jetbrains.kotlin" % "kotlin-stdlib" % "1.0.3"
    ),
    publishTo <<= version {
      (v: String) =>
        val nexus = "https://oss.sonatype.org/"
        if (v.trim.endsWith("SNAPSHOT"))
          Some("snapshots" at nexus + "content/repositories/snapshots")
        else
          Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    pomExtra := {
      <url>https://github.com/sksamuel/scalafunc</url>
        <licenses>
          <license>
            <name>MIT</name>
            <url>https://opensource.org/licenses/Apache2</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:sksamuel/scalafunc.git</url>
          <connection>scm:git@github.com:sksamuel/scalafunc.git</connection>
        </scm>
        <developers>
          <developer>
            <id>sksamuel</id>
            <name>sksamuel</name>
            <url>http://github.com/sksamuel</url>
          </developer>
        </developers>
    }
  )

  lazy val root = Project("scalafunc", file("."))
    .settings(rootSettings: _*)
    .settings(publish := {})
    .settings(publishArtifact := false)
    .settings(name := "scalafunc")
}
