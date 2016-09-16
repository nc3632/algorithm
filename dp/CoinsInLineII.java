/**
 * There are n coins with different value in a line. Two players take turns to take one or two coins from left side
 * until there are no more coins left. The player who take the coins with the most value wins.
 *
 * Could you please decide the first player will win or lose?
 *
 * Example
 * Given values array A = [1,2,2], return true.
 *
 * Given A = [1,2,4], return false.
 *
 */

public class CoinsInLineII {
    public boolean firstWillWin(int[] values) {
        // dp[i] represents the maximum value he can get at for pos i to the end.
        // Two choices:
        // 1. pick up only values[i], then the other party can get values[i + 1] or both of
        //    values of values[i + 1] and values[i + 2],
        //    so dp[i] = values[i] + min(dp[i + 2], dp[i + 3])
        // 2. pick up values[i] and values[i + 1], then the other party can get values[i + 2]
        //    or both of values[i + 3] and values[i + 2],
        //    so dp[i] = values[i] + values[i + 1] + min(dp[i + 3], dp[i + 4])
        // So, the best value would be
        // dp[i] = values[i] + values[i + 1] + max(min(dp[i + 2], dp[i + 3]), min(dp[i + 3], dp[i + 4]))

        if (values.length == 1) {
            return true;
        }

        int[] dp = new int[values.length + 2];
        dp[values.length - 1] = values[values.length - 1];
        dp[values.length - 2] = values[values.length - 1] + values[values.length - 2];

        int total = dp[values.length - 2];
        for (int i = values.length - 3; i >= 0; i--) {
            dp[i] = Math.max(values[i] + Math.min(dp[i + 2], dp[i + 3]),
                    values[i] + values[i + 1] + Math.min(dp[i + 3], dp[i + 4]));
            total += values[i];
        }

        return dp[0] > total - dp[0];
    }

    public static void main(String[] args) {
        CoinsInLineII sol = new CoinsInLineII();
        System.out.println(sol.firstWillWin(new int[]{1, 2, 2}));
        System.out.println(sol.firstWillWin(new int[]{1, 2, 4}));
        System.out.println(sol.firstWillWin(new int[]{10000}));
    }
}
