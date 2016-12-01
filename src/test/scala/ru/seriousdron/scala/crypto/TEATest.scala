package ru.seriousdron.scala.crypto

import org.scalatest.FunSuite

/**
  * Created by seriousdron on 01.12.16.
  * Test vectors and testing sequence from http://www.cix.co.uk/~klockstone/teavect.htm
  */
class TEATest extends FunSuite {

  test("test TEA cipher with test vectors") {

    //Cipher test
    val pz: Array[Int] = Array.fill(TEATest.ITERATIONS + 6)(0)
    var n = 0

    while (n < TEATest.ITERATIONS) {
      val tea: TEA = new TEA(pz.slice(n + 2, n + 6))
      val tuple: (Int, Int) = tea.cipher(pz(n), pz(n + 1))
      pz(n) = tuple._1
      pz(n + 1) = tuple._2
      pz(n + 6) = pz(n)

      n += 1
    }

    assert(pz.slice(TEATest.ITERATIONS - 1, TEATest.ITERATIONS + 5).sameElements(Array(0x2bb0f1b3, 0xc023ed11, 0x5c60bff2, 0x7072d01c, 0x4513c5eb, 0x8f3a38ab)))

    //Decipher test
    n = TEATest.ITERATIONS - 1
    while (n >= 0) {
      pz(n) = pz(n + 6)
      val tea: TEA = new TEA(pz.slice(n + 2, n + 6))
      val tuple: (Int, Int) = tea.decipher(pz(n), pz(n + 1))
      pz(n) = tuple._1
      pz(n + 1) = tuple._2

      n -= 1
    }

    assert(pz.slice(0, 6).sameElements(Array(0, 0, 0, 0, 0, 0)))
  }

}

object TEATest {
  private val ITERATIONS = 64
}
