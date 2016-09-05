/**
 * Given a 2D matrix, find the sum of the elements inside the rectangle defined by its upper left corner
 * (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which
 * contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *                  [3, 0, 1, 4, 2],
 *                  [5, 6, 3, 2, 1],
 *                  [1, 2, 0, 1, 5],
 *                  [4, 1, 0, 1, 7],
 *                  [1, 0, 3, 0, 5]
 *                ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 *
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

public class RangeSumQueryMutable2DBIT {
    private int[][] mat;
    private int[][] BIT;

    public RangeSumQueryMutable2DBIT(int[][] matrix) {
        mat = new int[matrix.length][matrix[0].length];
        BIT = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2, col2) - getSum(row1 - 1, col2) - getSum(row2, col1 - 1) + getSum(row1 - 1, col1 - 1);
    }

    public void update(int row, int col, int val) {
        int diff = val - mat[row][col];

        for (int i = row + 1; i < BIT.length; i += (i & -i)) {
            for (int j = col + 1; j < BIT[i].length; j += (j & -j)) {
                BIT[i][j] += diff;
            }
        }

        mat[row][col] = val;
    }

    public int getSum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= (i & -i)) {
            for (int j = col + 1; j > 0; j -= (j & -j)) {
                sum += BIT[i][j];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}

        };

        RangeSumQueryMutable2DBIT sol = new RangeSumQueryMutable2DBIT(matrix);
        System.out.println(sol.sumRegion(2, 1, 4, 3));
        sol.update(3, 2, 2);
        System.out.println(sol.sumRegion(2, 1, 4, 3));
    }
}
