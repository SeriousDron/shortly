package ru.seriousdron.scala.shortly

import akka.util.ByteString
import colossus.core.ServerContext
import colossus.protocols.http.HttpMethod._
import colossus.protocols.http.HttpService
import colossus.protocols.http.UrlParsing._
import colossus.protocols.redis.RedisClient
import colossus.service.Callback
import ru.seriousdron.scala.shortly.request.ShortenRequest

import scala.util.{Try, Failure, Success}

class ShortlyService(context: ServerContext, redis: RedisClient[Callback]) extends HttpService(context) {
  override def handle = {
    case request @ Post on Root / "api" / "v1" / "url" => {
      
      request.body.as[ShortenRequest] match {
        case Success(shortenReq: ShortenRequest) =>
          val key:ByteString = ByteString("test")
          val value: ByteString = ByteString(shortenReq.url.toExternalForm)
          redis.set(key, value).map(
            _ =>
              request.ok("stored")
          ).recover({
            case e:Throwable =>
              request.error(e.toString)
          })
        case Failure(e) =>
          Callback.successful(request.badRequest(e.toString))
      }
    }
    case input @ Get on Root / "hello" => {
      Callback.successful(input.ok("Hello world"))
    }
    case request @ Get on Root / "fail" => {
      Callback.successful(request.error("failure"))
    }
  }
}