package ru.seriousdron.scala.shortly.dto

import colossus.protocols.http.{HttpBody, HttpBodyEncoder, HttpHeader}
import ru.seriousdron.scala.shortly.storage.Key
import ru.seriousdron.scala.shortly.protocol.JsonProtocol._
import spray.json._

/** Implicit to convert [[ShortenResponse]] to use as [[colossus.protocols.http.HttpBody]]
  *
  * {{{
  *   import ru.seriousdron.scala.shortly.dto.ShortenSuccess._
  *   request.ok(ShortenSuccess(123))
  * }}}
  * @author SeriousDron <seriousdron@gmail.com>
  */
object ShortenSuccess {
  implicit object SuccessEncoder extends HttpBodyEncoder[ShortenSuccess] {
    val jsonHeader = HttpHeader("Content-Type", "application/json")
    override def encode(data: ShortenSuccess): HttpBody = {
      new HttpBody(data.toJson.toString.getBytes("UTF-8"), Some(jsonHeader))
    }
  }
}

/** Response after URL shortening
  *
  * @author SeriousDron <seriousdron@gmail.com>
  */
sealed trait ShortenResponse {}

/** Response after successful URL shortening
  *
  * @author SeriousDron <seriousdron@gmail.com>
  */
case class ShortenSuccess(key: Key) extends ShortenResponse
