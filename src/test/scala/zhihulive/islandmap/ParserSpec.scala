package zhihulive.islandmap

import org.scalatest.{FreeSpec, Matchers}

/**
  * @author 杨博 (Yang Bo)
  */
final class ParserSpec extends FreeSpec with Matchers {
  "The 10x10 sample input should be parsed" in {
    Parser.parse(
      "~~~xx~~~~~\n" +
        "~~~xx~~x~~\n" +
        "~~~~~x~~~~\n" +
        "~~~~xxx~~~\n" +
        "x~~~xxx~xx\n" +
        "xx~~~x~x~~\n" +
        "~~~~~~~x~~\n" +
        "~~~xx~~x~~\n" +
        "~~~xxxx~~x\n" +
        "~~x~~~~~~~"
    ) should be(
      IndexedSeq(
        IndexedSeq(false, false, false, true, true, false, false, false, false, false),
        IndexedSeq(false, false, false, true, true, false, false, true, false, false),
        IndexedSeq(false, false, false, false, false, true, false, false, false, false),
        IndexedSeq(false, false, false, false, true, true, true, false, false, false),
        IndexedSeq(true, false, false, false, true, true, true, false, true, true),
        IndexedSeq(true, true, false, false, false, true, false, true, false, false),
        IndexedSeq(false, false, false, false, false, false, false, true, false, false),
        IndexedSeq(false, false, false, true, true, false, false, true, false, false),
        IndexedSeq(false, false, false, true, true, true, true, false, false, true),
        IndexedSeq(false, false, true, false, false, false, false, false, false, false)
      ))
  }
  "A string end with a new-line character should be parsed" in {
    Parser.parse(
      "~~~\n" +
        "xxx\n"
    ) should be(
      IndexedSeq(
        IndexedSeq(false, false, false),
        IndexedSeq(true, true, true)
      ))
  }

  "A string end without a new-line character should be parsed" in {
    Parser.parse(
      "~~~\n" +
        "xxx"
    ) should be(
      IndexedSeq(
        IndexedSeq(false, false, false),
        IndexedSeq(true, true, true)
      ))
  }

  "An empty string should be parsed" in {
    Parser.parse("") should be(empty)
  }

  "A string that contains characters other than `\n`, `\r`, `x` and `~` should not be parsed" in {
    an[IllegalArgumentException] should be thrownBy Parser.parse(
      "~~\n" +
        "~X\n")
  }

  "A string that contains unaligned lines should not be parsed" in {
    an[IllegalArgumentException] should be thrownBy Parser.parse(
      "~~~~~\n" +
        "xxxxx\n" +
        "~x~\n")
  }

}
