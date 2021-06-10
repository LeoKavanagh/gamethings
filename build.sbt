val scala3Version = "3.0.0"

lazy val root = project
  .enablePlugins(ScalaJSPlugin)
  .in(file("."))
  .settings(
    name := "gamethings",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
        "com.novocode" % "junit-interface" % "0.11" % "test",
        "com.raquo" %%% "laminar" % "0.13.0",  // Requires Scala.js >= 1.5.0"
        "com.raquo" %%% "airstream" % "0.13.0",
        ("org.scala-js" %%% "scalajs-dom" % "1.1.0").cross(CrossVersion.for3Use2_13)
    ),

    scalaJSUseMainModuleInitializer := true,
  )
