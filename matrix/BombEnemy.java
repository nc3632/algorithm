/**
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum
 * enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the
 * wall is too strong to be destroyed.
 * Note that you can only put the bomb at an empty cell.
 *
 * Example:
 * For the given grid
 *
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 *
 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */

public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        int max = 0, numInRow = 0;
        int[] numInCols = new int[grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (j == 0 || grid[i][j - 1] == 'W') {
                    numInRow = 0;
                    for (int k = j; k < grid[0].length && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') {
                            numInRow++;
                        }
                    }
                }

                if (i == 0 || grid[i - 1][j] == 'W') {
                    numInCols[j] = 0;
                    for (int k = i; k < grid.length && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') {
                            numInCols[j]++;
                        }
                    }
                }

                if (grid[i][j] == '0') {
                    max = Math.max(max, numInRow + numInCols[j]);
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        BombEnemy sol = new BombEnemy();
        System.out.println(sol.maxKilledEnemies(new char[][] {
                {'0', 'E', '0', '0'},
                {'E', '0', 'W', 'E'},
                {'0', 'E', '0', '0'}
        }));
    }
}
