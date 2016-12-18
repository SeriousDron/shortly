package ru.seriousdron.scala.shortly.storage

import java.net.URL

import akka.util.ByteString
import colossus.protocols.redis.RedisClient
import colossus.service.Callback

import scala.util.Try

class RedisStorage(redis: RedisClient[Callback]) extends Storage {

  override def store(url: URL): Callback[Option[Long]] =
    getKey.flatMap(k => redis.set(ByteString(k.toString), ByteString(url.toExternalForm))
      .map {
        case true => Some(k)
        case false => None
      }
    ).recover({ case _ => None })

  override def restore(key: Long): Callback[Option[URL]] =
    redis.getOption(ByteString(key.toString)).map({
      case Some(s) => Some(new URL(s.utf8String))
      case None => None
    })

  private def getKey: Callback[Long] = {
    redis.incr(RedisStorage.counterKey)
  }
}

object RedisStorage {
  final val counterKey: ByteString = ByteString(getClass.getCanonicalName + "counter")
}
