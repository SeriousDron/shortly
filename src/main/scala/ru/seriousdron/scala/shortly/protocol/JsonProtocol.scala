package ru.seriousdron.scala.shortly.protocol

import ru.seriousdron.scala.shortly.dto.{ShortenSuccess, ShortenResponse}
import ru.seriousdron.scala.shortly.storage.Key
import spray.json._

/**
  * Created by seriousdron on 27.10.16.
  */
object JsonProtocol extends DefaultJsonProtocol {
  
  implicit object ShortenResponseJson extends RootJsonFormat[ShortenSuccess] {
    override def read(json: JsValue): ShortenSuccess = {
      json.asJsObject.getFields("status") match {
        case Seq(JsString("success")) => ShortenSuccess(Key(json.asJsObject().fields("key").convertTo[String]))
      }
    }

    override def write(obj: ShortenSuccess): JsValue = {
        JsObject(
          "status" -> JsString("success"),
          "key" -> JsString(obj.key.toString)
        )
    }
  }
}
