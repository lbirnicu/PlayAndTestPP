import scala.io.Source
import scala.util.{Success, Try}

package object bets {
  /**
    * Reads a file and creates a list of [[Bet]] from the lines in file that have the following format:
    * {{{
    * betId, betTimestamp, selectionId, selectionName, stake, price, currency
    * Bet-10, 1489490156000, 1, Selection-1, 0.5, 6.0, GBP
    * }}}
    *
    * Ignores the lines that are not correct. Returns an empty list if the file does not exist.
    *
    * @param fileName the name of the file that will be read; the file should be located in resources
    * @return a list of bets if the file exists and there are bets correctly described,
    *         empty list otherwise
    *
    */
  def readBets(fileName: String): List[Bet] = {
    val betList = for {
      stream <- Option(getClass.getResourceAsStream(s"/$fileName")).toList
      line   <- Source.fromInputStream(stream).getLines
    } yield validateBet(line)

    betList.collect({case Success(a) => a})
  }

  private def validateBet(line: String): Try[Bet] =
    Try {
      val Array(a, b, c, d, e, f, g) = line.split(", ")
      Bet(a, b.toLong, c.toLong, d, e.toDouble, f.toDouble, g)
    }
}
