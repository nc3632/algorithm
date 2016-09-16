/**
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with
 * its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above
 * Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 *
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some
 * cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
 * problems when the active area encroaches the border of the array. How would you address these problems?
 */

public class GameOfLife {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = countLiveNeighbors(board, i, j);
                if (board[i][j] == 0 && count == 3) {
                    board[i][j] = 2;
                } else if (board[i][j] == 1 && (count == 2 || count == 3)) {
                    board[i][j] = 3;
                }
            }

        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int countLiveNeighbors(int[][] board, int row, int col) {
        int[][] directions = new int[][] {
                {-1, -1},
                {-1, 0},
                {-1, 1},
                {0, 1},
                {1, 1},
                {1, 0},
                {1, -1},
                {0, -1}
        };

        int count = 0;
        for (int[] dir : directions) {
            int nbRow = row + dir[0], nbCol = col + dir[1];
            if (nbRow < 0 || nbRow >= board.length || nbCol < 0 || nbCol >= board[0].length) {
                continue;
            }

            count += board[nbRow][nbCol] & 1;
        }

        return count;
    }
}
