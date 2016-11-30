/**
 * Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
 *
 * Notice
 * You can not divide any item into small pieces.
 *
 * Example
 * If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size
 * we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the
 * backpack.
 *
 * You function should return the max size we can fill in the given backpack.
 */

public class Backpack {
    /**
     * dp[i][j] represents the maximum weight for A[i] with a maximum weight of j
     *
     * if (A[i] > j) then
     *   dp[i][j] = dp[i - 1][j]
     * else
     *   dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - A[i]] + A[i])
     * end
     */
    public int backPack(int m, int[] A) {
        int[][] dp = new int[A.length + 1][m + 1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);
                }
            }
        }

        return dp[A.length][m];
    }
}
