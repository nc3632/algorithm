import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array of task and k wait time for which a repeated task needs to wait k time to execute again. return
 * overall unit time it will take to complete all the task.
 * Example:
 * 1. A B C D and k = 3
 * ans: 4 (execute order A B C D)
 *
 * 2. A B A D and k = 3
 * ans: 5 (execute order A B D . A)
 *
 * 3. A A A A and k =3
 * ans: 13 (A . . . A . . . A . . . A)
 *
 * 4. A B C A C B D A and k = 4
 * ans: 11 (A B C . . A .C B D A )
 */

public class ScheduleWithCoolDown {
    public int getTotalRunTime(char[] tasks, int k) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char task : tasks) {
            counts.put(task, counts.getOrDefault(task, 0) + 1);
        }

        // O(log(n))
        Queue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(
                (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) -> o2.getValue() - o1.getValue());
        heap.addAll(counts.entrySet());

        Queue<Map.Entry<Character, Integer>> buffer = new LinkedList<>();

        // O(nlog(n))
        int totalTime = 0, tasksFinished = 0;
        while (tasksFinished < tasks.length) {
            totalTime++;

            Map.Entry<Character, Integer> top = heap.poll();
            if (top != null) {
                int taskCount = top.getValue() - 1;
                if (taskCount > 0) {
                    top.setValue(taskCount);
                } else {
                    top = null;
                }
                tasksFinished++;
            }

            if (buffer.size() >= k) {
                Map.Entry<Character, Integer> task = buffer.poll();
                if (task != null) {
                    heap.offer(task);
                }
            }
            buffer.offer(top);
        }

        return totalTime;
    }

    public static void main(String[] args) {
        ScheduleWithCoolDown sol = new ScheduleWithCoolDown();
        System.out.println(sol.getTotalRunTime(new char[]{'A', 'B', 'C', 'D'}, 3));
        System.out.println(sol.getTotalRunTime(new char[]{'A', 'B', 'A', 'D'}, 3));
        System.out.println(sol.getTotalRunTime(new char[]{'A', 'A', 'A', 'A'}, 3));
        System.out.println(sol.getTotalRunTime(new char[]{'A', 'B', 'C', 'A', 'C', 'B', 'D', 'A'}, 4));
    }
}
