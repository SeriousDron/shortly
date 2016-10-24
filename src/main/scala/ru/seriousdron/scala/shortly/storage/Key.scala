package ru.seriousdron.scala.shortly.storage

/**
  * Created by seriousdron on 24.10.16.
  */
class Key(val value:Long) {

  override def toString = {
    var v: Long = value
    val strBuilder = new StringBuilder(7)
    do {
      val mod = (v % Key.TO_STRING_BASE).toInt
      strBuilder += Key.charMap(mod)
      v = v / Key.TO_STRING_BASE
    } while (v > 0)
    strBuilder.toString()
  }
}

object Key {
  final val charMap = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
  final val TO_STRING_BASE = charMap.length
}