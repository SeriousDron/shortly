package ru.seriousdron.scala.shortly

import colossus.core.{ServerConnectionHandler, ServerContext, Initializer, WorkerRef}

class ShortlyInitializer(worker: WorkerRef) extends Initializer(worker) {
  override def onConnect: (ServerContext) => ServerConnectionHandler = context => {
    new ShortlyService(context)
  }
}
