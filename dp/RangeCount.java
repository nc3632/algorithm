import java.util.Arrays;

/**
 * Assume we have an array of ranges, and the maximum number in this array is n - 1.
 * Count the number of appearances of each number from 0 to n - 1.
 *
 * For example:
 * n = 5
 * ranges = [
 *   [1, 3]
 *   [2, 4]
 * ],
 *
 * The result returned is [0, 1, 2, 2, 1]
 */

public class RangeCount {
    public int[] count(int n, int[][] ranges) {
        int[] result = new int[n];
        for (int i = 0; i < ranges.length; i++) {
            result[ranges[i][0]] += 1;
            if (ranges[i][1] != n - 1) {
                result[ranges[i][1] + 1] -= 1;
            }
        }

        for (int i = 1; i < result.length; i++) {
            result[i] += result[i - 1];
        }

        return result;
    }

    public static void main(String[] args) {
        RangeCount sol = new RangeCount();
        int[][] ranges = new int[][] {
                {1, 3},
                {2, 4}
        };
        System.out.println(Arrays.toString(sol.count(5, ranges)));
    }
}
