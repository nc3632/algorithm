import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which
 * sum to n.
 *
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */

public class PerfectSquares {
    public int numSquares(int n) {
        int[] solutions = new int[n + 1];
        Arrays.fill(solutions, 1, solutions.length, Integer.MAX_VALUE);

        for(int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                if (solutions[j - i * i] != Integer.MAX_VALUE) {
                    solutions[j] = Math.min(solutions[j], solutions[j - i * i] + 1);
                }
            }
        }

        return solutions[n];
    }
}
