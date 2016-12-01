package ru.seriousdron.scala.crypto


/**
  * Created by seriousdron on 01.12.16.
  */
class Utils$Test extends org.scalatest.FunSuite {

  test("64-bit Long split in two 32-bits Ints with lower Int first") {
    assert(Utils.longTo2Ints(0x1122334455667788L) == (0x55667788, 0x11223344))
  }

  test("64-bit Long split in two 32-bits Ints with lower Int first with negative Long") {
    assert(Utils.longTo2Ints(0xffeeddccbbaa9988L) == (0xbbaa9988, 0xffeeddcc))
  }

  test("Two 32-bit Ints are packed in 64-bits Long with lower Int first") {
    assert(Utils.intsToLong(0x55667788, 0x11223344) == 0x1122334455667788L)
  }

  test("Tuple of two Ints packed in 64 bits Long with lower Int first") {
    assert(Utils.intsToLong((0x55667788, 0x11223344)) == 0x1122334455667788L)
    assert(Utils.intsToLong((0x1fffffff, 0x0fffffff)) == 0x0fffffff1fffffffL)
  }

  test("Tuple of two Ints with different signs packed in Long with lower Int first") {
    assert(Utils.intsToLong((0x80000000, 0x00000000)) == 0x0000000080000000L)
  }
}
