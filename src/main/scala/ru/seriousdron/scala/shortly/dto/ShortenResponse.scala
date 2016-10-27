package ru.seriousdron.scala.shortly.dto

import ru.seriousdron.scala.shortly.storage.Key

/**
  * Created by seriousdron on 27.10.16.
  */
trait ShortenResponse {

}

case class ShortenSuccess(key: Key) extends ShortenResponse