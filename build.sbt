lazy val root = project
  .in(file("."))
  .settings(
    name := "intrinsic bench",
    organization := "com.simianquant",
    version := "0.1",
    scalaVersion := "2.12.4"
  )
  .enablePlugins(JmhPlugin)