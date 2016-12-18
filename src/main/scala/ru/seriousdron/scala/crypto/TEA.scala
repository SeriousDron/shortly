package ru.seriousdron.scala.crypto


/**
  * Ciphers block with Tiny Encryption Algorithm
  * @param key Cipher key, 128-bit long as 4 32-bit Ints
  */
class TEA(val key: Array[Int]) {

  if (key.length != 4) {
    throw new IllegalArgumentException("TEA cipher key should be 128 bits long")
  }

  val k0 = key(0)
  val k1 = key(1)
  val k2 = key(2)
  val k3 = key(3)

  /** Returns 64 bits of data represented as two 32-bit Ints ciphered with TEA
    *
    * @param in0 Lower part of ciphered data (bytes 0-3)
    * @param in1 Upper part of ciphered data (bytes 4-7)
    * @return Tuple2[Int, Int]
    */
  def cipher(in0: Int, in1: Int): (Int, Int) = {
    var i = 0
    var sum = 0
    var v0 = in0
    var v1 = in1
    while (i < TEA.Cycles) {
      /* basic cycle start */
      sum += TEA.Magic
      v0 += ((v1 << 4) + k0) ^ (v1 + sum) ^ ((v1 >>> 5) + k1)
      v1 += ((v0 << 4) + k2) ^ (v0 + sum) ^ ((v0 >>> 5) + k3)
      i += 1
    }
    (v0, v1)
  }

  def cipher(t :(Int, Int)) : (Int, Int) = cipher(t._1, t._2)

  def cipher(l: Long) : Long = {
    Utils.intsToLong(cipher(Utils.longTo2Ints(l)))
  }

  def decipher(in0: Int, in1: Int): (Int, Int) = {
    var i = 0
    var sum = TEA.DecodeSum
    var v0 = in0
    var v1 = in1
    while (i < TEA.Cycles) {
      v1 -= ((v0 << 4) + k2) ^ (v0 + sum) ^ ((v0 >>> 5) + k3)
      v0 -= ((v1 << 4) + k0) ^ (v1 + sum) ^ ((v1 >>> 5) + k1)
      sum -= TEA.Magic
      i += 1
    }
    (v0, v1)
  }

  def decipher(t :(Int, Int)) : (Int, Int) = decipher(t._1, t._2)

  def decipher(l: Long) : Long = {
    Utils.intsToLong(decipher(Utils.longTo2Ints(l)))
  }
}

object TEA {
  private val KeyLength = 16
  private val Cycles = 32
  private val Magic = 0x9e3779b9
  private val DecodeSum = 0xc6ef3720

  def apply(key: Array[Int]): TEA = new TEA(key)

  def apply(key: Array[Byte]): TEA = {
    if (key.length != TEA.KeyLength) {
      throw new IllegalArgumentException("TEA cipher key should be 128 bits long")
    }
    val intKey = Array.ofDim[Int](4)
    var i = 0
    var j = 0
    while (j < TEA.KeyLength) {
      intKey(i) = (key(j) << 24) | ((key(j + 1) & 0xff) << 16) | ((key(j + 2) & 0xff) << 8) | (key(j + 3) & 0xff)
      j += 4
      i += 1
    }
    new TEA(intKey)
  }

  def apply(key: String): TEA = {
    val filtered = key.replaceAll("[^0-9A-Fa-f]", "")
    if (filtered.length != 32) {
      throw new IllegalArgumentException("TEA cipher key should be 128 bits long")
    }
    TEA(filtered.sliding(2, 2).toArray.map(Integer.parseInt(_, 16).toByte))
  }

}
