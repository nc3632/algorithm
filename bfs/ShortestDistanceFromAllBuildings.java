import java.util.LinkedList;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You
 * can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So
 * return 7.
 *
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return
 *  -1.
 */

public class ShortestDistanceFromAllBuildings {
    public int shortestDistance(int[][] grid) {
        int[][] distances = new int[grid.length][grid[0].length];
        int[][] reaches = new int[grid.length][grid[0].length];
        int numBuildings = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    numBuildings++;

                    boolean[][] visited = new boolean[grid.length][grid[0].length];
                    bfs(grid, distances, reaches, visited, i, j);
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && reaches[i][j] == numBuildings) {
                    shortest = Math.min(shortest, distances[i][j]);
                }
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    private void bfs(int[][] grid, int[][] distances, int[][] reaches, boolean[][] visited, int row, int col) {
        int[][] directions = new int[][] {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                for (int[] dir : directions) {
                    int x = p[0] + dir[0];
                    int y = p[1] + dir[1];

                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0
                            &&!visited[x][y]) {

                        queue.offer(new int[]{x, y});
                        reaches[x][y]++;
                        distances[x][y] += level;

                        visited[x][y] = true;
                    }
                }
            }

            level++;
        }
    }

    public static void main(String[] args) {
        ShortestDistanceFromAllBuildings sol = new ShortestDistanceFromAllBuildings();
        System.out.println(sol.shortestDistance(new int[][] {
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        }));
    }
}
