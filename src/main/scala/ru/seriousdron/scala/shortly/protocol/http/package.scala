package ru.seriousdron.scala.shortly.protocol

import java.net.URL

import akka.util.ByteString
import colossus.protocols.http.{
  HttpCodes,
  HttpHeader,
  HttpHeaders,
  HttpRequest,
  HttpResponse,
  QueryParameters
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

  def parseQueryString(qstring: String) = {
    def decode(s: String) = java.net.URLDecoder.decode(s, "UTF-8")
    var build = Vector[(String, String)]()
    var remain = qstring
    while (remain != "") {
      val keyval = remain.split("&", 2)
      val splitKV = keyval(0).split("=", 2)
      val key = decode(splitKV(0))
      val value = if (splitKV.size > 1) decode(splitKV(1)) else ""
      build = build :+ (key -> value)
      remain = if (keyval.size > 1) keyval(1) else ""
    }
    QueryParameters(build)
  }
}
