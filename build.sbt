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

publishMavenStyle := false

/** Console */
initialCommands in console := "import com.edc4it.sbt.activemq._"
