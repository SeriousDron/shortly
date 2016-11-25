package ru.seriousdron.scala.shortly.dto

import colossus.protocols.http.{HttpBody, HttpBodyEncoder, HttpHeader}
import ru.seriousdron.scala.shortly.storage.Key
import ru.seriousdron.scala.shortly.protocol.JsonProtocol._
import spray.json._

object ShortenSuccess {
  implicit object SuccessEncoder extends HttpBodyEncoder[ShortenSuccess] {
    val jsonHeader            = HttpHeader("Content-Type", "application/json")
    override def encode(data: ShortenSuccess): HttpBody = {
      new HttpBody(data.toJson.toString.getBytes("UTF-8"), Some(jsonHeader))
    }
  }
}

/**
  * Created by seriousdron on 27.10.16.
  */
sealed trait ShortenResponse {

}

case class ShortenSuccess(key: Key) extends ShortenResponse