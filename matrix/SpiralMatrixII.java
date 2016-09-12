import java.util.Arrays;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
 *
 * For example,
 * Given n = 3,
 *
 * You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */

public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int startRow = 0, endRow = n - 1;
        int startCol = 0, endCol = n - 1;
        int val = 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int i = startCol; i <= endCol; i++) {
                result[startRow][i] = val++;
            }
            startRow++;

            for (int i = startRow; i <= endRow; i++) {
                result[i][endCol] = val++;
            }
            endCol--;

            for (int i = endCol; i >= startCol; i--) {
                result[endRow][i] = val++;
            }
            endRow--;

            for (int i = endRow; i >= startRow; i--) {
                result[i][startCol] = val++;
            }
            startCol++;
        }

        return result;
    }

    public static void main(String[] args) {
        SpiralMatrixII sol = new SpiralMatrixII();
        int[][] matrix = sol.generateMatrix(3);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
