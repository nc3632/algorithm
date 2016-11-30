import java.util.ArrayList;
import java.util.List;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 * Example 1:
 * 0          3
 * |          |
 * 1 --- 2    4
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 * Example 2:
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 * [1, 0] and thus will not appear together in edges.
 */

public class NumberConnectedComponents {
    public int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return 0;
        }

        List[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }

        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited);
                count++;
            }
        }
        return count++;
    }

    private void dfs(List[] graph, int node, boolean[] visited) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        for (int i = 0; i < graph[node].size(); i++) {
            dfs(graph, (Integer) graph[node].get(i), visited);
        }
    }

    public static void main(String[] args) {
        NumberConnectedComponents sol = new NumberConnectedComponents();

        int[][] edges = new int[][] {
                {0, 1},
                {1, 2},
                {3, 4}
        };
        System.out.println(sol.countComponents(5, edges));

        edges = new int[][] {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4}
        };
        System.out.println(sol.countComponents(5, edges));
    }
}
