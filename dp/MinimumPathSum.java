/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
 * the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 */

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int[] minLastRow = new int[grid[0].length];
        minLastRow[0] = grid[0][0];
        for (int j = 1; j < grid[0].length; j++) {
            minLastRow[j] = minLastRow[j - 1] + grid[0][j];
        }

        int[] minCurrRow = new int[minLastRow.length];
        for (int i = 1; i < grid.length; i++) {
            minCurrRow[0] = minLastRow[0] + grid[i][0];
            for (int j = 1; j < grid[i].length; j++) {
                minCurrRow[j] = Math.min(minCurrRow[j - 1], minLastRow[j]) + grid[i][j];
            }

            minLastRow = minCurrRow;
        }

        return minLastRow[minLastRow.length - 1];
    }
}
