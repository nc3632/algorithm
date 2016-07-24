/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product
 * of those integers. Return the maximum product you can get.
 *
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 *
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */

public class IntegerBreak {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int j = 2; j <= n; j++) {
            for (int i = 1; i < j; i++) {
                dp[j] = Math.max(dp[j], Math.max(dp[i], i) * (j - i));
            }
        }
        return dp[n];
    }

    public int IntegerBreakMathTrick(int n) {
        // Try to use 3 as long as it is possible
        // Not that this is not correct when n is greater than 58
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        } else {
            int product = 1;
            while (n > 4) {
                product *= 3;
                n -= 3;
            }
            product *= n;

            return product;
        }
    }
}
