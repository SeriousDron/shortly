package ru.seriousdron.scala.shortly.storage

import java.net.URL

import akka.util.ByteString
import colossus.protocols.redis.RedisClient
import colossus.service.Callback

import scala.util.Try

class RedisStorage(redis: RedisClient[Callback]) extends Storage {

  override def store(url: URL): Try[Callback[Long]] = Try {
    getKey.flatMap(k => redis.set(ByteString(k.toString), ByteString(url.toExternalForm))
      .map {
        case true => k
        case false => throw new Exception
      }
    )
  }

  override def restore(key: Long): Try[Callback[URL]] = Try {
    redis.get(ByteString(key.toString)).map((s) => new URL(s.utf8String))
  }

  private def getKey:Callback[Long] = {
    redis.incr(RedisStorage.counterKey)
  }
}

object RedisStorage {
  final val counterKey:ByteString = ByteString(getClass.getCanonicalName + "counter")
}
