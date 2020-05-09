//scalaVersion := "2.12.10"
scalaVersion := "2.13.1"
version := "0.0.3"

organization := "com.billding"

enablePlugins(ScalaJSPlugin)

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "1.0.0",
  "dev.zio" %%% "zio" % "1.0.0-RC17",
)

githubOwner := "swoogles"
githubRepository := "ScalaJsZioLibrary"
