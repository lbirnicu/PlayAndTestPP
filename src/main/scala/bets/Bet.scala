package bets

import java.text.SimpleDateFormat

/**
  * Data type representing a bet
  * @param betId a unique identifier of the bet
  * @param betTimeStamp the timestamp when the bet was done
  * @param selectionId the id of the option selected through the bet
  * @param selectionName the name of the option selected through the bet
  * @param stake the chances to win the bet
  * @param price the price of the bet
  * @param currency the currency of the pay
  */
case class Bet(betId: String, betTimeStamp: Long, selectionId: Long, selectionName: String,
               stake: Double, price: Double, currency: String) {
  require(stake > 0)
  require(price > 0)
}

object Bet {

  /**
    * Returns the payout as the product between the stake and the price of the bet `b`
    * @param b the bet for which the payout is calculated
    */
  def betPayout(b: Bet): Double = b.stake * b.price

  /**
    * Returns a triple representing:
    *   1. the count of the elements from `bl` list
    *   2. the stakes sum of all bets from the `bl` list
    *   3. the payout sum of all bets from the `bl` list
    * @param bl the list of bets for which the calculation is performed
    */
  def aggregate(bl: List[Bet]): (Int, Double, Double) = (bl.size, bl.map(_.stake).sum, bl.map(betPayout).sum)

  /**
    * Returns the date in `dd-MM-yyy` format for the timestamp `ts`
    * @param ts the timestamps for which is computed the date
    */
  def tsToDate(ts: Long): String = new SimpleDateFormat("dd-MM-yyyy").format(ts)

  /**
    * Return a mapping between selection ids and the aggregated values (see [[Bet.aggregate]])
    * of the associated bets
    *
    *  e.g. Map(1 -> (8, 28.0, 178.25), 2 -> (6, 26.35, 166.3))
    * @param bl the bet list for which the aggregation by selection id is performed
    */
  def aggregationBySelectionId(bl: List[Bet]): Map[Long, (Int, Double, Double)] =
    bl
    .groupBy(_.selectionId)
    .mapValues(aggregate)

  /**
    * Return a mapping between currencies and the aggregated values (see [[Bet.aggregate]])
    * of the associated bets
    *
    *  e.g. Map(EUR -> (11, 38.25000, 248.89), GBP -> (12, 56.9, 327.0999))
    * @param bl the bet list for which the aggregation by currency is performed
    */
  def aggregationByCurrency(bl: List[Bet]): Map[String, (Int, Double, Double)] =
    bl
    .groupBy(_.currency)
    .mapValues(aggregate)

  /**
    * Return a mapping between dates and the aggregated values (see [[Bet.aggregate]])
    * of the associated bets
    *
    * e.g. Map(10-03-2017 -> (4, 19.900, 131.7), 11-03-2017 -> (7, 23.7, 145.7000)
    * @param bl the bet list for which the aggregation by dates is performed
    */
  def aggregationByDate(bl: List[Bet]): Map[String, (Int, Double, Double)] =
    bl
    .groupBy(b => tsToDate(b.betTimeStamp))
    .mapValues(aggregate)
}
