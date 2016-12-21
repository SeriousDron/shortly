package ru.seriousdron.scala.shortly.dto

import java.net.URL

import colossus.protocols.http.HttpBodyDecoder
import ru.seriousdron.scala.shortly.protocol.http

import scala.util.Try

/** Represents user request to shorten URL
  *
  * @author SeriousDron <seriousdron@gmail.com>
  */
class ShortenRequest(val url: URL) {}

/** Implicits to construct ShortenRequest from HttpBody
  *
  * @author SeriousDron <seriousdron@gmail.com>
  */
object ShortenRequest {

  implicit object ShortenDecoder extends HttpBodyDecoder[ShortenRequest] {
    def decode(body: Array[Byte]) = Try {
      new ShortenRequest(
        new URL(
          http
            .parseQueryString(new String(body))
            .getFirst("url")
            .getOrElse(throw new RuntimeException("Can't get URL from query"))
        ))
    }
  }

}
