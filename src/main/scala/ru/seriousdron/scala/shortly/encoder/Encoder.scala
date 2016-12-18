package ru.seriousdron.scala.shortly.encoder

/** Trait that provides interface to encode and decode Long values
  *
  * @author SeriousDron <seriousdron@gmail.com>
  */
trait Encoder {
  def encode(key: Long): Long
  def decode(key: Long): Long
}
