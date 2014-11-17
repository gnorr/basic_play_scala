name := "basic_play_scala"

version := "0.1"

scalaVersion := "2.11.4"

// adding -language:reflectiveCalls because of a compiler warning when conf/routes imports the routes file from a sub project
scalacOptions := Seq("-feature", "-deprecation", "-language:reflectiveCalls")

lazy val core = (project in file("modules/core")).enablePlugins(PlayScala)

lazy val api = (project in file("modules/api")).enablePlugins(PlayScala).
  dependsOn(core)

lazy val web = (project in file("modules/web")).enablePlugins(PlayScala)

lazy val root = (project in file(".")).enablePlugins(PlayScala).
  dependsOn(api, core, web).
  aggregate(api, core, web).
  settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)
