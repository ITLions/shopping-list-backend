name := "ShopingList"

version := "0.13.8"

lazy val `shopinglist` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(cache , ws, "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "com.typesafe.play" %% "play-slick" % "1.1.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.0")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

routesGenerator := InjectedRoutesGenerator

herokuAppName in Compile := "pure-reaches-2979"