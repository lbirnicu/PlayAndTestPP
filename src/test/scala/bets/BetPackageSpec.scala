package bets

import org.scalatest.{Matchers, WordSpec}
import BetPackageSpec._

class BetPackageSpec extends WordSpec with Matchers {
  "BetPackageSpec" when {
    "invoking readBets" should {
      "return a list of bets" in {
        readBets("input.txt") shouldBe List(b1, b2)
      }
      "return a list only with the bets correctly defined" in {
        readBets("input1.txt") shouldBe List(b1)
      }
      "return an empty list" in {
        readBets("nonExiting.txt") shouldBe List()
      }
      "return an empty list when all data incorrect" in {
        readBets("input2.txt") shouldBe List()
      }
      "ignore the bets that do not have enough values in one line" in {
        readBets("input3.txt") shouldBe List(b1)
      }
      "ignore the bets that have negative stakes" in {
        readBets("input4.txt") shouldBe List(b1)
      }
    }
  }
}

object BetPackageSpec {
  val b1 = Bet("id1", 1489490156000l, 1, "ids1", 0.5, 6.0, "GBP")
  val b2 = Bet("id2", 1489227356000l, 2, "ids2", 0.75, 0.8, "EUR")
}