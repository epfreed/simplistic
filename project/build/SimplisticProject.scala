import sbt._

class SimplisticProject(info: ProjectInfo) extends DefaultProject(info)
{

    val httpClient  = "commons-httpclient" % "commons-httpclient" % "3.1"
    val codec       = "commons-codec" % "commons-codec" % "1.3"
    val pool        = "commons-pool" % "commons-pool" % "1.4"

    //downlowds from github do not work. jar is in lib directory
    //val fakesbd = "fakesdb" % "fakesdb-standalone" % "2.2" from "https://github.com/downloads/stephenh/fakesdb/fakesdb-standalone-2.2.jar"

    val scalatest = "org.scalatest" % "scalatest" % "1.3"
    val junitInterface = "com.novocode" % "junit-interface" % "0.4" % "test"

}

