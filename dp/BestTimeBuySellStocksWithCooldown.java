/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as
 * you like (ie, buy one and sell one share of the stock multiple times) with the following
 * restrictions:
 *
 * 1. You may not engage in multiple transactions at the same time (ie, you must sell the
 * stock before you buy again).
 * 2. After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 *
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 */

public class BestTimeBuySellStocksWithCooldown {
    public int maxProfit(int[] prices) {
        int[] buys = new int[3];
        buys[1] = Integer.MIN_VALUE;

        int[] sells = new int[3];
        for (int i = 2; i < prices.length + 2; i++) {
            // Either use the the profits from the previous buy or re-do the buy
            buys[i % 3] = Math.max(buys[(i - 1) % 3], sells[(i - 2) % 3] - prices[i - 2]);

            // Either use the profits from the previous sell or re-do the sell.
            sells[i % 3] = Math.max(sells[(i - 1) % 3], buys[(i - 1) % 3] + prices[i - 2]);
        }

        return sells[(prices.length + 1) % 3];
    }
}
