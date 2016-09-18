import java.util.ArrayList;
import java.util.List;

/**
 * Dial a phone using the old phone
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *   0
 *
 * Starting from 1, and the next one would be the move of knight in chess. That being said,
 * it can go to 6 or 8. If 6 were dialed, the next choices would be 1 and 7.
 *
 * The questions is for n digit phone number, how many possibilities
 * are there.
 */
public class WaysDialPhone {
    private int waysDialPhone(int n) {
        int[][][] dp = new int[4][3][n + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length;j ++) {
                dp[i][j][0] = 1;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    if (i == dp.length - 1 && (j == 0 || j == 2)) {
                        break;
                    }

                    List<int[]> moves = nextMoves(i, j);
                    for (int[] move : moves) {
                        dp[i][j][k] += dp[move[0]][move[1]][k - 1];
                    }
                }

            }
        }
        return dp[0][0][n];
    }

    private List<int[]> nextMoves(int row, int col) {
        int[][] directions = new int[][] {
                {-2, -1},
                {-2, 1},
                {-1, 2},
                {1, 2},
                {2, 1},
                {2, -1},
                {1, -2},
                {-1, -2}
        };

        List<int[]> result = new ArrayList<>();
        for (int[] dir : directions) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (isValidPos(nextRow, nextCol)) {
                result.add(new int[]{nextRow, nextCol});
            }
        }

        return result;
    }

    private boolean isValidPos(int row, int col) {
        return row >= 0 && row <= 2 && col >= 0 && col <= 2
                || row == 3 && col == 1;
    }

    public static void main(String[] args) {
        WaysDialPhone sol = new WaysDialPhone();
        System.out.println(sol.waysDialPhone(1));
        System.out.println(sol.waysDialPhone(2));
        System.out.println(sol.waysDialPhone(3));
        System.out.println(sol.waysDialPhone(4));
        System.out.println(sol.waysDialPhone(5));
    }
}
