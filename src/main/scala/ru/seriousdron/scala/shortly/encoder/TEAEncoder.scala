package ru.seriousdron.scala.shortly.encoder

import ru.seriousdron.scala.crypto.TEA

/**
  * Created by seriousdron on 18.12.16.
  *
  * Encode and decode key with TEA
  */
class TEAEncoder(val key: String) extends Encoder {

  val tea: TEA = TEA(key)

  override def encode(key: Long): Long = {
    tea.cipher(key)
  }

  override def decode(key: Long): Long = {
    tea.decipher(key)
  }
}
