package ru.seriousdron.scala.shortly

import colossus.core.ServerContext
import colossus.protocols.http.HttpMethod._
import colossus.protocols.http.{HttpHeader, HttpBody, HttpBodyEncoder, HttpService}
import colossus.protocols.http.UrlParsing._
import colossus.service.Callback
import ru.seriousdron.scala.shortly.dto.{ShortenSuccess, ShortenRequest}
import ru.seriousdron.scala.shortly.protocol.JsonProtocol._
import ru.seriousdron.scala.shortly.storage.{Key, Storage}
import scala.util.{Failure, Success}
import spray.json._

class ShortlyService(context: ServerContext, storage: Storage) extends HttpService(context) {
  override def handle = {
    case request @ Post on Root / "api" / "v1" / "url" => {
      //TODO: different response types
      request.body.as[ShortenRequest].flatMap(req => storage.store(req.url)) match {
        case Success(stored: Callback[Long]) => stored.map(key =>
          request.ok(ShortenSuccess(Key(key)))(SuccessEncoder)
        )
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

  implicit object SuccessEncoder extends HttpBodyEncoder[ShortenSuccess] {
    val jsonHeader            = HttpHeader("Content-Type", "application/json")
    override def encode(data: ShortenSuccess): HttpBody = {
      new HttpBody(data.toJson.toString.getBytes("UTF-8"), Some(jsonHeader))
    }
  }
}