name := "ShopingList"

version := "0.13.8"

lazy val `shopinglist` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( jdbc , cache , ws, "postgresql" % "postgresql" % "9.1-901-1.jdbc4")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers ++= Seq("google-sedis-fix" at "http://pk11-scratch.googlecode.com/svn/trunk")

routesGenerator := InjectedRoutesGenerator

herokuAppName in Compile := "pure-reaches-2979"