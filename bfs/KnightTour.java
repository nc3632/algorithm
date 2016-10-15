import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a 8 x 8 chess board, calculate the minimum number of moves from its original position (row1, col1)
 * to position (row2, col2).
 */

public class KnightTour {
    private static class Position{
        int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + ":" + y;
        }
    }

    public List<Position> minimumMoves(int row1, int col1, int row2, int col2) {
        // The values at (i, j) is its previous position in the move sequence.
        Position[][] chessBoard = new Position[8][8];
        for (int i = 0; i < chessBoard.length; i++) {
            Arrays.fill(chessBoard[i], new Position(-1, -1));
        }
        chessBoard[row1][col1] = new Position(row1, col1);

        Queue<Position> queue = new LinkedList<>();
        queue.add(chessBoard[row1][col1]);

        int[][] moves = new int[][]{
                {-2, -1},
                {-2, 1},
                {-1, -2},
                {-1, 2},
                {2, -1},
                {2, 1},
                {1, -2},
                {1, 2}
        };

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            if (pos.x == row2 && pos.y == col2) {
                return getPath(pos, chessBoard);
            }

            for (int[] move : moves) {
                Position nextPos = new Position(pos.x + move[0], pos.y + move[1]);
                if (nextPos.x >= 0 && nextPos.y >= 0
                        && nextPos.x < chessBoard.length && nextPos.y < chessBoard.length
                        && chessBoard[nextPos.x][nextPos.y].x == -1
                        && chessBoard[nextPos.x][nextPos.y].y == -1) {

                    chessBoard[nextPos.x][nextPos.y] = pos;
                    queue.add(nextPos);
                }
            }
        }

        return new LinkedList<>();
    }

    private List<Position> getPath(Position pos, Position[][] chessBoard) {
        List<Position> path = new LinkedList<>();
        path.add(pos);

        while (pos.x != chessBoard[pos.x][pos.y].x && pos.y != chessBoard[pos.x][pos.y].y) {
            pos = chessBoard[pos.x][pos.y];
            path.add(0, pos);
        }

        return path;
    }

    public static void main(String[] args) {
        KnightTour sol = new KnightTour();
        System.out.println(sol.minimumMoves(0, 0, 7, 7).toString());
    }
}
