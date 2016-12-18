package ru.seriousdron.scala.shortly.storage

import scala.language.implicitConversions
/**
  * Created by seriousdron on 24.10.16.
  */
case class Key(value:Long) {

  override def toString = {
    var unsigned: BigInt = (BigInt(value >>> 1) << 1) + (value & 1)
    val strBuilder = new StringBuilder(7)
    do {
      val mod = (unsigned % Key.TO_STRING_BASE).toInt
      strBuilder += Key.charMap(mod)
      unsigned = unsigned / Key.TO_STRING_BASE
    } while (unsigned != 0)
    strBuilder.toString().reverse
  }
}

object Key {
  private final val charMap = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
  private final val TO_STRING_BASE = charMap.length
  
  def apply(key: String) = {
    def fromString(str: List[Char], sum:Long = 0): Long = {
      str match {
        case Nil => sum
        case c :: tail => fromString(tail, Key.charMap.indexOf(c) + sum * Key.TO_STRING_BASE)
      }
    }
    new Key(fromString(key.toList))
  }

  implicit def stringToKey(s: String): Key = Key(s)
  implicit def longToKey(l: Long): Key = Key(l)
}