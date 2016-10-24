package ru.seriousdron.scala.shortly

import colossus.core.ServerContext
import colossus.protocols.http.HttpMethod._
import colossus.protocols.http.HttpService
import colossus.protocols.http.UrlParsing._
import colossus.service.Callback
import ru.seriousdron.scala.shortly.request.ShortenRequest
import ru.seriousdron.scala.shortly.storage.Storage

import scala.util.{Failure, Success}

class ShortlyService(context: ServerContext, storage: Storage) extends HttpService(context) {
  override def handle = {
    case request @ Post on Root / "api" / "v1" / "url" => {
      
      request.body.as[ShortenRequest].flatMap(req => storage.store(req.url)) match {
        case Success(stored: Callback[Long]) => stored.map(key =>
          request.ok("stored, key:" + key.toString))
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