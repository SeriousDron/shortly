package ru.seriousdron.scala.crypto

/**
  * Created by seriousdron on 01.12.16.
  */
object Utils {

  intsToLong(0x55667788, 0x11223344)

  def longTo2Ints(long: Long) : (Int, Int) = (long.toInt, (long >>> 32).toInt)
  def intsToLong(a: Int, b: Int):Long = (b.toLong << 32) | (a & 0xffffffffL)
  def intsToLong(t: (Int, Int)) : Long = intsToLong(t._1, t._2)

}
