/**
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * print out
 * 1 5 9
 * 2 6
 * 4 8
 * 3
 * 7
 */

public class MatrixPrintDiagonally {
    public void print(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            helper(matrix, i);
        }
    }

    private void helper(int[][] matrix, int start) {
        for (int i = 0, j = start; i < matrix.length && j < matrix[0].length; i++, j++) {
            System.out.print(matrix[i][j] + " ");
        }
        System.out.println();

        if (start != 0) {
            for (int i = start, j = 0; i < matrix.length && j < matrix[0].length; i++, j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MatrixPrintDiagonally sol = new MatrixPrintDiagonally();
        sol.print(new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
    }
}
