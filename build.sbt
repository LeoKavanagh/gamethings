// val scalaVersion = "3.0.0-RC1"
//val scalaVersion = "2.13.1"

lazy val root = project
  .enablePlugins(ScalaJSPlugin)
  .in(file("."))
  .settings(
    name := "gamethings",
    version := "0.1.0",
    scalaVersion := "2.13.1",

    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.1.0",
    ),


  scalaJSUseMainModuleInitializer := true,
  //jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()

)

