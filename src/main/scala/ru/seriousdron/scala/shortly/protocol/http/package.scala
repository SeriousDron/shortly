package ru.seriousdron.scala.shortly.protocol

import akka.util.ByteString
import colossus.protocols.http.{HttpCodes, HttpHeader, HttpHeaders, HttpRequest, HttpResponse}
import java.net.URL


/**
  * Created by seriousdron on 18.12.16.
  */
package object http {
  implicit class HttpRequestMethod(val req: HttpRequest) {
    def redirect(url: URL) : HttpResponse = {
      val body: ByteString = ByteString("") //Cannot use HttpBody.NoBody due ambiguous implicits, maybe my bad
      req.respond(HttpCodes.FOUND, body, HttpHeaders(HttpHeader("Location", url.toString)))
    }
  }
}
