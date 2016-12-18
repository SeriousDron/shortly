package ru.seriousdron.scala.shortly

import akka.actor.ActorSystem
import colossus.IOSystem
import colossus.core._
import java.net.InetSocketAddress

import com.typesafe.config.ConfigFactory


object Main extends App {
  implicit val as = ActorSystem()
  implicit val io = IOSystem()

  val config = ConfigFactory.load()

  Server.start("shortly", config)(getInitializer)
  private def getInitializer(worker: WorkerRef) : Initializer = {
    new ShortlyInitializer(worker, config)
  }
}
