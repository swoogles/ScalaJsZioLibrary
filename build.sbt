//scalaVersion := "2.12.10"
scalaVersion := "2.13.1"
version := "0.0.11"

organization := "com.billding"

enablePlugins(ScalaJSPlugin)

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "1.0.0",
  "dev.zio" %%% "zio" % "1.0.0-RC21-2",
)

githubOwner := "swoogles"
githubRepository := "ScalaJsZioLibrary"
