/**
 * Given n distinct positive integers, integer k (k <= n) and a number target.
 *
 * Find k numbers where sum is target. Calculate how many solutions there are?
 *
 * Example
 * Given [1,2,3,4], k = 2, target = 5.
 *
 * There are 2 solutions: [1,4] and [2,3].
 *
 * Return 2.
 */

public class KSum {
    /**
     * dp[i][j][t] is the number of solutions for the first i number of elements,
     * to pick j of them, and come up with a sum of t.
     *
     * When we come to A[i], we can either do not use A[i], having the first i - 1 element
     * to use up the limit of j and t. We can also use A[i] if j - A[i] >= 0, and it ends
     * up with the same number as dp[i - 1][j - 1][t]
     *
     * dp[i][j][t] = dp[i - 1][j - 1][t - A[i]] + dp[i - 1][j][t]
     */
    public int kSum(int A[], int k, int target) {
        int[][][] dp = new int[A.length + 1][k + 1][target + 1];
        for (int i = 0; i <= A.length; i++) {
            dp[i][0][0] = 1;
        }

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= target; t++) {
                    dp[i][j][t] =  dp[i - 1][j][t];
                    if (t - A[i - 1] >= 0) {
                        dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
                    }
                }
            }
        }

        return dp[A.length][k][target];
    }
}
