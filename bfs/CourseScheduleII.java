import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 * expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take
 * to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all
 * courses, return an empty array.
 *
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course
 * order is [0,1]
 *
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both
 * courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another
 * correct ordering is[0,2,1,3].
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Set<Integer>[] incoming = new Set[numCourses];
        Set<Integer>[] outgoing = new Set[numCourses];
        for (int i = 0; i < numCourses; i++) {
            incoming[i] = new HashSet<>();
            outgoing[i] = new HashSet<>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            incoming[prerequisites[i][1]].add(prerequisites[i][0]);
            outgoing[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        Queue<Integer> readyCourses = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (outgoing[i].isEmpty()) {
                readyCourses.add(i);
            }
        }

        List<Integer> sequence = new ArrayList<>();
        while (!readyCourses.isEmpty()) {
            int course = readyCourses.poll();
            sequence.add(course);

            for (Integer id : incoming[course]) {
                outgoing[id].remove(course);

                if (outgoing[id].isEmpty()) {
                    readyCourses.add(id);
                }
            }
        }

        if (sequence.size() == numCourses) {
            return sequence.stream().mapToInt(i -> i).toArray();
        } else {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        CourseScheduleII sol = new CourseScheduleII();
        System.out.println(Arrays.toString(sol.findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(sol.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
    }
}
