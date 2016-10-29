/**
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value
 * in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and
 * weights associated with n items respectively. Also given an integer W which represents knapsack capacity, find
 * out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W.
 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 */

public class KnapSack {
    /**
     * dp[i][w] represents the best value that can be achieved with a weight limit of w.
     * Therefore, the final result would be dp[n][cap]
     */
    public int knapsack(int cap, int n, int[] val, int[] wt) {
        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= cap; w++) {
                if (wt[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = n, w = cap; i > 0 && cap > 0; i--) {
            if (dp[i][w] == dp[i - 1][w]) {
                sb.append(0);
            } else {
                sb.append(1);
                w -= wt[i - 1];
            }
        }
        sb.reverse();
        System.out.println(sb.toString());

        return dp[n][cap];
    }

    public static void main(String[] args) {
        KnapSack sol = new KnapSack();

        int[] val = new int[]{15, 100, 90, 60, 40, 15, 10, 1};
        int[] wt = new int[]{2, 20, 20, 30, 40, 30, 60, 10};
        int cap = 102;

        System.out.println(sol.knapsack(cap, val.length, val, wt));
    }
}
