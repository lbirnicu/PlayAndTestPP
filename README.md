Write an application in scala, that calculates bet payout and provides aggregation over a bet dataset for each of the following
1. By SelectionID
2. By Currency
Optional
3. By day (i.e. 14-Mar-2017)

The aggregation should return the following, and be the basis for providing acceptance tests
Aggregation Key
   No of Bets
   Total Stake
   Total Payout
 
A bet can be represented with the following case class
<pre><code>
case class Bet(betId: String, betTimeStamp: Long, selectionId: Long, selectionName: String, 
						   stake: Double, price: Double, currency: String)
</code></pre>
 
Calculating the bet payout is simply done with the following equation : stake * price
 
Below is a comma-delimented dataset of bet data to work with:

<pre><code>
betId, betTimestamp, selectionId, selectionName, stake, price, currency
Bet-10, 1489490156000, 1, Selection-1, 0.5s, 6.0, EUR
Bet-11, 1489490156000, 2, Selection-2, 1.25, 4.0, EUR
Bet-12, 1489230956000, 4, Selection-4, 5.0, 4.5, EUR
Bet-13, 1489403756000, 3, Selection-3, 4.5, 5.5, GBP
Bet-14, 1489144556000, 2, Selection-2, 7.9, 7.0, EUR
Bet-15, 1489140956000, 1, Selection-1, 3.4, 6.5, EUR
Bet-16, 1489227356000, 4, Selection-4, 2.5, 6.5, GBP
Bet-17, 1489313756000, 2, Selection-2, 1.5, 11.0, EUR
Bet-18, 1489310156000, 2, Selection-2, 3.8, 5.5, GBP
Bet-19, 1489482956000, 3, Selection-3, 3.4, 4.0, GBP
Bet-20, 1489396556000, 4, Selection-4, 2.25, 5.0, EUR
Bet-21, 1489137356000, 2, Selection-2, 5.4, 6.5, EUR
Bet-22, 1489223756000, 3, Selection-3, 6.7, 6.5, GBP
Bet-23, 1489310156000, 3, Selection-3, 1.1, 4.5, EUR
Bet-24, 1489324556000, 4, Selection-4, 2.0, 6.5, GBP
Bet-25, 1489151756000, 2, Selection-2, 3.2, 6.0, GBP
Bet-26, 1489497356000, 2, Selection-2, 4.2, 5.0, EUR
Bet-27, 1489410956000, 3, Selection-3, 2.1, 4.5, EUR
Bet-28, 1489324556000, 1, Selection-1, 7.8, 5.5, GBP
Bet-29, 1489320956000, 4, Selection-4, 6.2, 6.5, GBP
Bet-30, 1489493756000, 4, Selection-4, 8.4, 7.5, EUR
Bet-31, 1489407356000, 1, Selection-1, 10.5, 6.0, GBP
Bet-32, 1489320956000, 3, Selection-3, 1.3, 5.5, GBP
Bet-33, 1489234556000, 2, Selection-2, 0.75, 7.0, EUR
</code></pre>