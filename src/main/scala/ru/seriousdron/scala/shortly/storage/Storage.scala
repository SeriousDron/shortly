package ru.seriousdron.scala.shortly.storage

import colossus.service.Callback
import java.net.URL

import scala.util.Try

trait Storage {
  def store(url: URL) : Try[Callback[Long]]
  def restore(key: Long) : Try[Callback[URL]]
}