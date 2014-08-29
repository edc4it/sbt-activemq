sbtPlugin := true

organization := "com.edc4it"

name := "sbt-activemq"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-feature")


libraryDependencies ++= Seq(
  "org.apache.activemq" % "activemq-all" % "5.10.0",
  "org.apache.xbean" % "xbean-spring" % "3.7",
  "org.apache.activemq" % "activeio-core" % "3.1.4",
  "org.springframework" % "spring-context" % "2.5.5"
)

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/edc4it/sbt-activemq/</url>
    <licenses>
      <license>
        <name>Apache License, Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        <distribution>repo</distribution>
        <comments>A business-friendly OSS license</comments>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:git@github.com:edc4it/sbt-activemq</connection>
      <url>https://github.com/edc4it/sbt-activemq/</url>
    </scm>
    <developers>
      <developer>
        <name>Raphael Parree</name>
        <email>rparree@edc4it.com</email>
        <organization>edc4it</organization>
      </developer>
    </developers>
  )

pomIncludeRepository := { _ => false }

publishMavenStyle := true

publishArtifact in Test := false
