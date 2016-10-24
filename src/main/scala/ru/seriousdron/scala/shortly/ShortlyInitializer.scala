package ru.seriousdron.scala.shortly

import colossus.core.{ServerConnectionHandler, ServerContext, Initializer, WorkerRef}
import colossus.protocols.redis._
import colossus.protocols.redis.Redis.defaults._
import colossus.service.Callback
import java.net.InetSocketAddress

import ru.seriousdron.scala.shortly.storage.RedisStorage

class ShortlyInitializer(worker: WorkerRef, redisAddress: InetSocketAddress) extends Initializer(worker) {
  override def onConnect: (ServerContext) => ServerConnectionHandler = { 
    
    implicit val env: WorkerRef = worker
    
    val redis:RedisClient[Callback] = Redis.client(redisAddress.getHostString, redisAddress.getPort)
    val storage = new RedisStorage(redis)
        
    context => {
      new ShortlyService(context, storage)
    }
  }
}
