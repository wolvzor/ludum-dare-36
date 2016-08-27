name := "tiger-story"
version := "0.1"

libraryDependencies ++= Seq(
  //"net.sf.proguard" % "proguard-base" % "4.11" % "provided",
  "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % "1.9.4",
  "com.badlogicgames.gdx" % "gdx-platform" % "1.9.4" classifier "natives-desktop"
)