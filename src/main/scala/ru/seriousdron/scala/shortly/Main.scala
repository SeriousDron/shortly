package ru.seriousdron.scala.shortly

import akka.actor.ActorSystem
import colossus.IOSystem
import colossus.core._



object Main extends App {
  implicit val as = ActorSystem()
  implicit val io = IOSystem()

  Server.start("shortly", 9000)(getInitializer)
  private def getInitializer(worker: WorkerRef) : Initializer = new ShortlyInitializer(worker)
}
