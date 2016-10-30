/**
 * Given a set of numbers, find the Length of the Longest Arithmetic Progression (LLAP) in it.
 *
 * Examples:
 *
 * set[] = {1, 7, 10, 15, 27, 29}
 * output = 3
 * The longest arithmetic progression is {1, 15, 29}
 *
 * set[] = {5, 10, 15, 20, 25, 30}
 * output = 6
 * The whole set is in AP
 *
 * Time Complexity O(n^2)
 */

public class LongestArithmeticSubsequence {
    public int longestArithmeticProgression(int[] set) {
        if (set.length <= 2) {
            return set.length;
        }

        // dp[i][j] represents the maximum length of the LLAP starting with set[i] and set[j]
        int[][] dp = new int[set.length][set.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i][set.length - 1] = 2;
        }

        int max = 2;
        for (int i = set.length - 2; i >= 1; i--) {
            int j = i - 1, k = i + 1;
            while (j >= 0 && k < set.length) {
                if (set[j] + set[k] == 2 * set[i]) {
                    dp[j][i] = 1 + dp[i][k];

                    max = Math.max(max, dp[j][i]);

                    j--;
                    k++;
                } else if (set[j] + set[k] < 2 * set[i]) {
                    k++;
                } else {
                    j--;
                }
            }

            while (j >= 0) {
                dp[j][i] = 2;
                j--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LongestArithmeticSubsequence sol = new LongestArithmeticSubsequence();
        System.out.println(sol.longestArithmeticProgression(new int[]{1, 7, 10, 15, 27, 29}));
        System.out.println(sol.longestArithmeticProgression(new int[]{5, 10, 15, 20, 25, 30}));
    }
}
