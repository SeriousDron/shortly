package ru.seriousdron.scala.shortly.storage

import colossus.service.Callback
import java.net.URL

import scala.util.Try

trait Storage {
  def store(url: URL) : Callback[Option[Long]]
  def restore(key: Long) : Callback[Option[URL]]
}