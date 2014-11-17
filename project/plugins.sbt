//https://typesafe.com/blog/what-happened-to-my-travis-ci
//libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.6")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")

