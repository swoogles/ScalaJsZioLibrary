//scalaVersion := "2.12.10"
scalaVersion := "3.3.1"
version := "0.0.16"

organization := "com.billding"

enablePlugins(ScalaJSPlugin)

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "2.6.0",
  "dev.zio" %%% "zio" % "2.0.17",
)

githubOwner := "swoogles"
githubRepository := "ScalaJsZioLibrary"
