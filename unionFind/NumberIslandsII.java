import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A 2d grid map of m rows and n columns is initially filled with water.We may perform anaddLand operation which
 * turns the water at position (row, col) into a land.Given a list of positions to operate,count the number of
 * islands after each addLand operation.An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically.You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 *
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 *
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 *
 * Challenge:
 *
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */

public class NumberIslandsII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>(positions.length);

        int[] roots = new int[m * n];
        Arrays.fill(roots, -1);

        int[][] directions = new int[][] {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };

        int count = 0;
        for (int i = 0; i < positions.length; i++) {
            count++;

            int loc = positions[i][0] * n + positions[i][1];
            roots[loc] = loc;

            for (int[] dir : directions) {
                int neighborX = positions[i][0] + dir[0];
                int neighborY = positions[i][1] + dir[1];

                if (neighborX < 0 || neighborX >= m || neighborY < 0 || neighborY >= n) {
                    continue;
                }

                int neighborLoc = neighborX * n + neighborY;
                if (roots[neighborLoc] != -1) {
                    int neighborRoot = findRoot(neighborLoc, roots);
                    if (roots[loc] != neighborRoot) {
                        roots[loc] = neighborRoot;
                        count--;
                    }
                }
            }

            result.add(count);
        }

        return result;
    }

    private int findRoot(int loc, int[] roots) {
        while (loc != roots[loc]) {
            loc = roots[loc];
        }

        return loc;
    }

    public static void main(String[] args) {
        NumberIslandsII sol = new NumberIslandsII();
        System.out.println(sol.numIslands2(3, 3, new int[][]{
                {0, 0},
                {0, 1},
                {1, 2},
                {2, 1}
        }));
    }
}
