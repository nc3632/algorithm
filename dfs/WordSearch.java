/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * For example,
 * Given board =
 *
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(char[][] board, String word, int startRow, int startCol, int pos) {
        if (pos >= word.length()) {
            return true;
        }

        if (startRow < 0 || startRow >= board.length || startCol < 0 || startCol >= board[0].length
                || board[startRow][startCol] != word.charAt(pos)) {
            return false;
        }

        board[startRow][startCol] ^= 256;

        int[][] directions = new int[][]{
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };
        for (int[] dir : directions) {
            int row = startRow + dir[0], col = startCol + dir[1];
            if (search(board, word, row, col, pos + 1)) {
                board[startRow][startCol] ^= 256;
                return true;
            }
        }

        board[startRow][startCol] ^= 256;

        return false;
    }

    public static void main(String[] args) {
        WordSearch sol = new WordSearch();

        char[][] board = new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(sol.exist(board, "ABCCED"));
        System.out.println(sol.exist(board, "SEE"));
        System.out.println(sol.exist(board, "ABCB"));

        System.out.println(sol.exist(new char[][]{{'A'}}, "A"));

        System.out.println(sol.exist(new char[][]{{'A', 'B'}, {'C', 'D'}}, "ACDB"));

        System.out.println(sol.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCEFSADEESE"));
    }
}
