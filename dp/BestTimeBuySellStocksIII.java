/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

public class BestTimeBuySellStocksIII {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int i = 0; i < prices.length; i++) {
            // Either abandon the previous buy1 and do a new buy or just hold the previous buy1
            buy1 = Math.max(buy1, -prices[i]);

            // Either abandon the previous sell1 and sell it now for a better profit
            // or do nothing and keep the previous sell1
            sell1 = Math.max(sell1, buy1 + prices[i]);

            // Either abandon the previous buy2 and do a new buy for better profit
            // or do nothing and keep the previous buy2
            buy2 = Math.max(buy2, sell1 - prices[i]);

            // Either abandon the previous sell2 and do a new sell or keep the previous sell2
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }

        return sell2;
    }
}
