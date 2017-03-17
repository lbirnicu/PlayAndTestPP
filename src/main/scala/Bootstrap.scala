import bets._
import bets.Bet._

/**
  * Loads the correctly formatted bets from resources/input.txt and prints the aggregations by:
  *   1. currency
  *   2. selection id
  *   3. date
  *
  *   Result:
  *   {{{
  *   Aggregation by currency:      Map(EUR -> (12,43.25000000000001,271.4), GBP -> (11,51.9,304.6))
  *   Aggregation by selection id:  Map(2 -> (8,28.0,178.25), 4 -> (6,26.35,166.3), 1 -> (3,21.7,128.0),
  *                                     3 -> (6,19.1,103.45000000000002))
  *   Aggregation by date:          Map(10-03-2017 -> (4,19.900000000000002,131.7),
  *                                     12-03-2017 -> (7,23.7,145.70000000000002),
  *                                     13-03-2017 -> (4,19.35,108.45),
  *                                     11-03-2017 -> (4,14.95,87.55000000000001),
  *                                     14-03-2017 -> (4,17.25,102.6))
  *   }}}
  */
object Bootstrap extends App {
  val inputFile = "input.txt"
  val bl = readBets(inputFile)
  println("Aggregation by currency: " + aggregationByCurrency(bl))
  println("Aggregation by selection id: " + aggregationBySelectionId(bl))
  println("Aggregation by date: " + aggregationByDate(bl))
}