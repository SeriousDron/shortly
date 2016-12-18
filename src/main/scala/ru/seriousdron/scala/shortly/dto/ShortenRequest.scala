package ru.seriousdron.scala.shortly.dto

import java.net.URL

import colossus.protocols.http.HttpBodyDecoder

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
      val data = new String(body)
      val parts = data.split("=")
      if (parts.length != 2) {
        throw new ArrayIndexOutOfBoundsException()
      }
      new ShortenRequest(new URL(parts(1)))
    }
  }

}
