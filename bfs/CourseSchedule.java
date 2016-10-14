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
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule {
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

}
