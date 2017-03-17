package bets

import Bet._
import BetSpec._
import org.scalatest.{WordSpec, Matchers}

class BetSpec extends WordSpec with Matchers {

  "BetSpec" when {
    "invoking betPayout operation" should {
      "correctly compute the payout" in {
        betPayout(b1) shouldBe 0.30
      }
    }
    "invoking aggregate operation" should {
      "correctly aggregate the values" in {
        aggregate(List(b1, b2, b3)) shouldBe (3, 2.25, sumPayout)
      }
      "correctly aggregate an empty list" in {
        aggregate(List()) shouldBe (0, 0, 0)
      }
      "correctly aggregate a list with one element" in {
        aggregate(List(b1)) shouldBe oneElResult
      }
    }
    "invoking tsToDay" should {
      "correctly return the date" in {
        tsToDate(1489490156000l) shouldBe "14-03-2017"
      }
    }
    "invoking aggregationBySelectionId" should {
      "correctly compute the mapping" in {
        aggregationBySelectionId(List(b1, b2, b3, b4, b5)) shouldBe aggBySelIdResult
      }
      "return an empty map" in {
        aggregationBySelectionId(List()) shouldBe Map.empty
      }
      "return a map with one key" in {
        aggregationBySelectionId(List(b1)) shouldBe Map(1 -> oneElResult)
      }
    }
    "invoking aggregationByCurrency" should {
      "correctly compute the mapping" in {
        aggregationByCurrency(List(b1, b2, b3, b4, b5)) shouldBe aggByCurrencyResult
      }
      "return an empty map" in {
        aggregationByCurrency(List()) shouldBe Map.empty
      }
      "return a map with one key" in {
        aggregationByCurrency(List(b1)) shouldBe Map("GBP" -> oneElResult)
      }
    }
    "invoking aggregationByDate" should {
      "correctly compute the mapping" in {
        aggregationByDate(List(b1, b2, b3, b4, b5)) shouldBe aggByDateResult
      }
      "return an empty map" in {
        aggregationByDate(List()) shouldBe Map.empty
      }
      "return a map with one key" in {
        aggregationByDate(List(b1)) shouldBe Map("14-03-2017" -> (1, 0.5, 0.30))
      }
    }
  }
}

object BetSpec {
  private val b1 = Bet("id1", 1489490156000l, 1, "ids1", 0.5, 0.6, "GBP")
  private val b2 = Bet("id2", 1489227356000l, 2, "ids2", 0.75, 0.8, "EUR")
  private val b3 = Bet("id3", 1489324556000l, 3, "ids3", 1.0, 2.0, "GBP")
  private val b4 = Bet("id4", 1489151756000l, 2, "ids4", 4.5, 2.0, "EUR")
  private val b5 = Bet("id5", 1489403756000l, 1, "ids5", 1.0, 2.0, "EUR")
  private val sumPayout = 0.5 * 0.6 + 0.75 * 0.8 + 1.0 * 2.0
  private val oneElResult = (1, 0.5, 0.30)
  private val aggBySelIdResult = Map(1 ->(2, 1.5, 0.5*0.6 + 1.0*2.0),
    2 -> (2, 5.25, 0.75*0.8 + 4.5*2.0), 3 -> (1, 1.0, 2.0))
  private val aggByCurrencyResult = Map("EUR" ->(3, 6.25, 4.5*2.0 + 1.0*2.0 + 0.75*0.8),
    "GBP" -> (2, 1.5, 2.3))
  private val aggByDateResult = Map("14-03-2017" ->(1, 0.5, 0.3),
    "11-03-2017" -> (1, 0.75, 0.75*0.8 ), "12-03-2017" -> (1, 1.0, 2.0),
    "10-03-2017" -> (1, 4.5, 9.0), "13-03-2017" -> (1, 1.0, 2.0))
}