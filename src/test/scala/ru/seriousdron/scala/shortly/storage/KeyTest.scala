package ru.seriousdron.scala.shortly.storage

import org.scalatest.FunSuite

/**
  * Created by seriousdron on 18.12.16.
  */
class KeyTest extends FunSuite {

  test("test toString is reversible") {
    val key: Key = new Key(0x123456789abcdef0L)
    val str: String = key.toString

    assert(Key(str).value == 0x123456789abcdef0L)
  }

  test("test toString is reversible with negative keys") {
    val key: Key = new Key(0xfedcba9876543210L)
    val str = key.toString

    assert(Key(str).value == 0xfedcba9876543210L)
  }

  test("test creating Key from Long") {
    val key: Key = 0x123456789abcdef0L

    assert(key.value == 0x123456789abcdef0L)
  }

  test("test creating Key from String") {
    val key: Key = Key("s3FWEyiE1v9")

    assert(key.value == 5103407931652358703L)
  }

  test("test implicit conversion of Long to Key") {
    val key: Key = 0x123456789abcdef0L

    assert(key.value == 0x123456789abcdef0L)
  }

  test("test implicit conversion of String to Key") {
    val key: Key = "s3FWEyiE1v9"

    assert(key.value == 5103407931652358703L)
  }

}
