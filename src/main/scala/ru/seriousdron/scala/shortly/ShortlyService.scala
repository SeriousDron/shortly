package ru.seriousdron.scala.shortly

import colossus.core.ServerContext
import colossus.protocols.http.HttpMethod._
import colossus.protocols.http.UrlParsing._
import colossus.protocols.http.{Http, HttpService}
import colossus.service.Callback

class ShortlyService(context: ServerContext) extends HttpService(context) {
  override def handle = {
    case request @ Get on Root / "hello" => {
      Callback.successful(request.ok("Hello world"))
    }
    case request @ Get on Root / "fail" => {
      Callback.successful(request.error("failure"))
    }
  }
}