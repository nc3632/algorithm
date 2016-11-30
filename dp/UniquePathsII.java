/**
 * Follow up for "Unique Paths":
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 *
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * The total number of unique paths is 2.
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        int[] lastRow = new int[obstacleGrid[0].length];
        int[] currRow = new int[lastRow.length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            currRow[0] = obstacleGrid[i][0] == 1 ? 0 : 1;
            for (int j = 1; j < obstacleGrid[i].length; j++) {
                currRow[j] = obstacleGrid[i][j] == 1 ? 0 : lastRow[j] + currRow[j - 1];
            }

            lastRow = currRow;
        }

        return lastRow[lastRow.length - 1];
    }
}
