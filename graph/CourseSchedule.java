/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 * expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 *
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0
 * you should also have finished course 1. So it is impossible.
 *
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 * graph is represented.
 *
 * Hints:
 * This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological
 * ordering exists and therefore it will be impossible to take all courses.
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of
 * Topological Sort.
 * Topological sort could also be done via BFS.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule {
    /**
     * BFS
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Set<Integer>[] outgoing = new Set[numCourses];
        Set<Integer>[] incoming = new Set[numCourses];
        for (int i = 0; i < numCourses; i++) {
            outgoing[i] = new HashSet<>();
            incoming[i] = new HashSet<>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            outgoing[prerequisites[i][0]].add(prerequisites[i][1]);
            incoming[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        Queue<Integer> readyCourses = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (outgoing[i].isEmpty()) {
                readyCourses.add(i);
            }
        }

        int finished = readyCourses.size();
        while (!readyCourses.isEmpty()) {
            int course = readyCourses.poll();
            for (int source : incoming[course]) {
                outgoing[source].remove(course);
                if (outgoing[source].isEmpty()) {
                    readyCourses.add(source);
                    finished++;
                }
            }
        }

        return finished == numCourses;
    }

    /**
     * DFS
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        boolean[] noCycle = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];

        for (int i = 0; i < graph.length; i++) {
            if (hasCycle(i, graph, noCycle, visited)) {
                return false;
            }
        }

        return true;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        return graph;
    }

    private boolean hasCycle(int root, List<Integer>[] graph, boolean[] noCycle, boolean[] visited) {
        if (noCycle[root]) {
            return false;
        }

        if (visited[root]) {
            return true;
        }

        visited[root] = true;
        for (int child : graph[root]) {
            if (hasCycle(child, graph, noCycle, visited)) {
                return true;
            }
        }

        noCycle[root] = true;
        visited[root] = false;

        return false;
    }
}
