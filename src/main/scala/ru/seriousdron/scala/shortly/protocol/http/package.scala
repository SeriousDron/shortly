package ru.seriousdron.scala.shortly.protocol

import java.net.URL

import akka.util.ByteString
import colossus.protocols.http.{
  HttpCodes,
  HttpHeader,
  HttpHeaders,
  HttpRequest,
  HttpResponse
}

/** Implicits for HTTP redirect response
  *
  * @author SeriousDron <seriousdron@gmail.com>
  */
package object http {
  implicit class HttpRequestMethod(val req: HttpRequest) {
    def redirect(url: URL): HttpResponse = {
      val body: ByteString = ByteString("") //Cannot use HttpBody.NoBody due ambiguous implicits, maybe my bad
      req.respond(HttpCodes.FOUND,
                  body,
                  HttpHeaders(HttpHeader("Location", url.toString)))
    }
  }
}
