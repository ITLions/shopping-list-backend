
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.3")

addSbtPlugin("com.heroku" % "sbt-heroku" % "0.5.1")

logLevel := Level.Warn

resolvers ++= Seq("Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/")
