/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each
 * operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 *
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
public class EditDistance {
    /**
     * Use dp[i][j] to represent the minimum distance from word1(0->i) to word2(0->j).
     * if (word1[i] == word2[j]) then
     *   dp[i][j] = dp[i - 1][j - 1]
     * else
     *   we could delete word1[i] or add word1[i] after word2[j]
     *   we could delete word2[j] or add word2[j] after word1[i]
     *   we could update word1[i] to word2[j] or update word2[j] to word1[i].
     *   so
     *   dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1])
     * end
     *
     * Initial values dp[i][0] = 1, dp[0][j] = 1
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
