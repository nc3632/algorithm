import sun.nio.cs.ext.MacHebrew;

import java.util.Arrays;

/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid.
 * Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon
 * to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to
 *  0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in
 * each step.
 *
 * Notes:
 *
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where
 * the princess is imprisoned.
 */

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int rows = dungeon.length, cols = dungeon[0].length;

        int[][] dp = new int[rows + 1][cols + 1];

        // Cannot go beyond the border other than the bottom-right corner,
        // set the required health be the maximum value, so that path
        // won't be picked
        for (int i = 0; i <= rows; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // After the bottom-right corner, the minimum required health is 1
        dp[rows][cols - 1] = dp[rows - 1][cols] = 1;

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                // Either going down or going right, we need to find the minimum health to have
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];

                // The minimum health cannot be less than or equal to 0
                dp[i][j] = Math.max(1, dp[i][j]);
            }
        }

        return dp[0][0];
    }
}
