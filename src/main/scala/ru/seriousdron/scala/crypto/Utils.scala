package ru.seriousdron.scala.crypto

/** Utils to use in cipher/decipher procedures. Mostly manipulating bits and converting types
  *
  * @author SeriousDron <seriousdron@gmail.com>
  */
object Utils {

  def longTo2Ints(long: Long): (Int, Int) = (long.toInt, (long >>> 32).toInt)
  def intsToLong(a: Int, b: Int): Long = (b.toLong << 32) | (a & 0xffffffffL)
  def intsToLong(t: (Int, Int)): Long = intsToLong(t._1, t._2)

}
