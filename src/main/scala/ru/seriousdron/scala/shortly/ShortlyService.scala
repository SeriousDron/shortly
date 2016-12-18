package ru.seriousdron.scala.shortly

import colossus.core.ServerContext
import colossus.protocols.http.HttpMethod._
import colossus.protocols.http.HttpService
import colossus.protocols.http.UrlParsing._
import colossus.service.Callback
import ru.seriousdron.scala.shortly.dto.{ShortenRequest, ShortenSuccess}
import ru.seriousdron.scala.shortly.encoder.Encoder
import ru.seriousdron.scala.shortly.protocol.http._
import ru.seriousdron.scala.shortly.storage.{Key, Storage}

import scala.util.{Failure, Success}

/** Colossus service, main HTTP-requests handler
  *
  * @constructor Create service with storage and encode
  * @param context Colossus server context
  * @param storage Storage to use for storing URL
  * @param encoder Encoder to encrypt shortened keys for user
  */
class ShortlyService(context: ServerContext,
                     storage: Storage,
                     encoder: Encoder)
    extends HttpService(context) {
  override def handle = {
    case request @ Post on Root / "api" / "v1" / "url" =>
      request.body.as[ShortenRequest] match {
        case Success(req) =>
          storage
            .store(req.url)
            .map({
              case Some(key) => request.ok(ShortenSuccess(encoder.encode(key)))
              case None =>
                request.error("Error saving URL to storage")
            })
        case Failure(e) =>
          Callback.successful(request.badRequest(e.toString))
      }

    case request @ Get on Root / key =>
      storage
        .restore(encoder.decode(Key(key).value))
        .map({
          case Some(url) => request.redirect(url)
          case None => request.notFound(key)
        })
  }

}
