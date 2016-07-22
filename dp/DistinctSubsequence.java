/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters.
 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 *
 * Return 3.
 */

public class DistinctSubsequence {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            // Empty string counts as a substring of another
            dp[i][0] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public int numDistinctSpaceOptimization(String s, String t) {
        int[] dp = new int[t.length() + 1];
        dp[0] = 1;
        for (int i = 0; i < s.length(); i++) {
            int prevVal = dp[0];
            for (int j = 1; j < dp.length; j++) {
                int currVal = dp[j];
                if (s.charAt(i) == t.charAt(j - 1)) {
                    dp[j] += prevVal;
                }
                prevVal = currVal;
            }
        }

        return dp[dp.length - 1];
    }
}
