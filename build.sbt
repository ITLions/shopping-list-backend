import com.typesafe.config.ConfigFactory

name := "ShoppingList"

version := "0.13.8"

lazy val `shoppinglist` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(cache, ws,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "com.typesafe.slick" %% "slick" % "3.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.play" %% "play-slick" % "1.1.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.0")

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

routesGenerator := InjectedRoutesGenerator

herokuAppName in Compile := "pure-reaches-2979"

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()
slick <<= slickCodeGenTask

// code generation task
lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
    val outputDir = `shoppinglist`.base.getAbsoluteFile / "app"
    val url = conf.getString("slick.dbs.default.db.url")
    val jdbcDriver = conf.getString("slick.dbs.default.db.driver")
    val slickDriver = conf.getString("slick.dbs.default.driver").dropRight(1)
    val pkg = "models"
    val user = conf.getString("slick.dbs.default.db.user")
    val password = conf.getString("slick.dbs.default.db.password")
    toError(r.run("slick.codegen.SourceCodeGenerator",
      cp.files,
        Array(slickDriver, jdbcDriver, url, outputDir.getPath, pkg, user, password), s.log))
    val fname = outputDir + s"/$pkg/Tables.scala"
    Seq(file(fname))
}
