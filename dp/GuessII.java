import java.util.Arrays;

/**
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 *
 * However, when you guess a particular number x, and you guess wrong, you pay $x.
 * You win the game when you guess the number I picked.
 *
 * Example:
 *
 * n = 10, I pick 8.
 *
 * First round:  You guess 5, I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
 *
 * Game over. 8 is the number I picked.
 *
 * You end up paying $5 + $7 + $9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 */

public class GuessII {
    public int getMoneyAmountWithoutCache(int n) {
        return helperWithoutCache(1, n);
    }

    private int helperWithoutCache(int start, int end) {
        // If there is only one number or no number, it can be guessed correctly in one shot.
        if (start >= end) {
            return 0;
        }

        // Try different first guess positions, and see which one ends up with the best.
        int best = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            // The card being played might be any value, the worst situation must be
            // prepared. That being said, we must prepare every guess is wrong so
            // that enough money will be prepared.
            int sol = i + Math.max(helperWithoutCache(start, i - 1), helperWithoutCache(i + 1, end));
            best = Math.min(best, sol);
        }

        return best;
    }

    public int getMoneyAmount(int n) {
        int[][] cache = new int[n + 1][n + 1];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }

        return helper(1, n, cache);
    }

    private int helper(int start, int end, int[][] cache) {
        // If there is only one number or no number, it can be guessed correctly in one shot.
        if (start >= end) {
            return 0;
        }

        if (cache[start][end] != -1) {
            return cache[start][end];
        }

        // Try different first guess positions, and see which one ends up with the best.
        int best = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            // The card being played might be any value, the worst situation must be
            // prepared. That being said, we must prepare every guess is wrong so
            // that enough money will be prepared.
            int sol = i + Math.max(helper(start, i - 1, cache), helper(i + 1, end, cache));
            best = Math.min(best, sol);
        }

        // cache the solution
        cache[start][end] = best;
        return best;
    }

    public int getMoneyAmountDp(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int end = 2; end <= n; end++) {
            for (int start = end - 1; start >= 1; start--) {
                int best = Integer.MAX_VALUE;
                for (int i = start + 1; i < end; i++) {
                    int sol = i + Math.max(dp[start][i - 1], dp[i + 1][end]);
                    best = Math.min(best, sol);
                }

                // The above loop cannot cover that case that start is only one less than end
                dp[start][end] = (start + 1 == end) ? start : best;
            }
        }

        return dp[1][n];
    }
}
