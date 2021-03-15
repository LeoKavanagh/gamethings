lazy val root = project
  .enablePlugins(ScalaJSPlugin)
  .in(file("."))
  .settings(
    name := "gamethings",
    version := "0.1.0",
    scalaVersion := "2.13.1",

  libraryDependencies ++= Seq(
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "org.scala-js" %%% "scalajs-dom" % "1.1.0"
      ),

  scalaJSUseMainModuleInitializer := true,
  //jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()

)

