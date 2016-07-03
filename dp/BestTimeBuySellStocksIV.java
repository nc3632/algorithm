import java.util.Arrays;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 */

public class BestTimeBuySellStocksIV {
    public int maxProfit(int k, int[] prices) {
        if (k >= prices.length / 2) {
            // This turns into the problem that you can buy/sell as many times as you want to
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        int[] buys = new int[k + 1];
        Arrays.fill(buys, Integer.MIN_VALUE);

        int[] sells = new int[k + 1];

        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buys[j] = Math.max(buys[j], sells[j - 1] - prices[i]);
                sells[j] = Math.max(sells[j], buys[j] + prices[i]);
            }
        }

        return sells[k];
    }

    public static void main(String[] args) {
        BestTimeBuySellStocksIV sol = new BestTimeBuySellStocksIV();
        System.out.println(sol.maxProfit(2, new int[] {3, 2, 6, 5, 0, 3}));
    }
}
