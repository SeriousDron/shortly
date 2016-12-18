package ru.seriousdron.scala.shortly

import colossus.core.{
  Initializer,
  ServerConnectionHandler,
  ServerContext,
  WorkerRef
}
import colossus.protocols.redis._
import colossus.protocols.redis.Redis.defaults._
import colossus.service.Callback
import java.net.InetSocketAddress

import com.typesafe.config.Config
import ru.seriousdron.scala.shortly.encoder.TEAEncoder
import ru.seriousdron.scala.shortly.storage.RedisStorage

/** Colossus initializer for Shortly service
  *
  * @author SeriousDron <seriousdron@gmail.com>
  * @constructor Create for worker reference and global config
  * @param worker Colossus worker reference
  * @param config Config
  */
class ShortlyInitializer(worker: WorkerRef, config: Config)
    extends Initializer(worker) {
  override def onConnect: (ServerContext) => ServerConnectionHandler = {

    implicit val env: WorkerRef = worker
    val redisAddress: InetSocketAddress = new InetSocketAddress(
      config.getString("shortly.redis.host"),
      config.getInt("shortly.redis.port"))

    val redis: RedisClient[Callback] =
      Redis.client(redisAddress.getHostString, redisAddress.getPort)
    val storage = new RedisStorage(redis)
    val encoder = new TEAEncoder(config.getString("shortly.teaencoder.key"))

    context =>
      {
        new ShortlyService(context, storage, encoder)
      }
  }
}
