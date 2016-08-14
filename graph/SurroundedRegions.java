import java.util.Stack;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */

public class SurroundedRegions {
    private static class Pair {
        public int first;
        public int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) && board[i][j] == 'O') {
                    dfs(board, i , j);
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'M') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'M';

        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
    }

    private void dfs1(char[][] board, int i, int j) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(i, j));

        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            int row = pair.first;
            int col = pair.second;

            board[row][col] = 'M';

            if (row > 0 && board[row - 1][col] == 'O') {
                stack.push(new Pair(row - 1, col));
            }
            if (col < board[0].length - 1 && board[row][col + 1] == 'O') {
                stack.push(new Pair(row, col + 1));
            }
            if (row < board.length - 1 && board[row + 1][col] == 'O') {
                stack.push(new Pair(row + 1, col));
            }
            if (col > 0 && board[row][col - 1] == 'O') {
                stack.push(new Pair(row, col - 1));
            }
        }
    }
}
