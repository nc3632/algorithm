/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
 *
 * For example, given the following matrix:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 *
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        // dp[i][j] represents the the size of the maximal square whose right-bottom corner is
        // located at matrix[i - 1][j - 1]
        int dp[][] = new int[matrix.length + 1][matrix[0].length + 1];

        int max = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i - 1][j - 1] == '0') {
                    // No such a square is available
                    dp[i][j] = 0;
                } else {
                    int leftLen = dp[i][j - 1], topLen = dp[i - 1][j];
                    if (leftLen == topLen) {
                        // The maximal squares at the top and at the left are of the same size.
                        // If the value at the matrix[i - topLen - 1][j - leftLen - 1] is '1', then
                        // we can increase the size by 1. Otherwise, keep the same size.
                        dp[i][j] = (matrix[i - topLen - 1][j - leftLen - 1] == '1') ? leftLen + 1 : leftLen;
                    } else {
                        // The maximal squares at the top and at the left are of different sizes,
                        // and the maximal square at this spot is the smaller one plus 1.
                        dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                    }
                }

                max= Math.max(max, dp[i][j]);
            }
        }

        return max * max;
    }
}
