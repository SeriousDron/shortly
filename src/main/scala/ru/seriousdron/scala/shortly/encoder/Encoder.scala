package ru.seriousdron.scala.shortly.encoder

/**
  * Created by seriousdron on 18.12.16.
  *
  * Trait that provides interface to encode and decode Long values
  */
trait Encoder {
  def encode(key: Long) : Long
  def decode(key: Long) : Long
}
