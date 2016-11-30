/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 */

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        int[] paths = new int[n];
        for (int i = 0; i < m; i++) {
            paths[0] = 1;
            for (int j = 1; j < n; j++) {
                paths[j] = paths[j - 1] + paths[j];
            }
        }

        return paths[n - 1];
    }
}
