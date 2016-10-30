import java.util.ArrayList;
import java.util.List;

/**
 * There are a number of tasks, and each one take a specified unit of time to finish.
 * Given k number of worker, find the best way to assign these jobs so that the minimum
 * unit of time would be used.
 *
 * For example,
 * Task: 2, 2, 3, 7, 1
 * # of workers: 2
 * Solution: [2, 2, 3] [1, 7]
 *n
 * Task: 9, 8, 7, 4, 4
 * # of workers: 2
 * Solution: [9, 7] [8, 4, 4]
 */

public class JobAssignment {
    private static class Result {
        int min = Integer.MAX_VALUE;
        List<List<Integer>> distribution;
    }

    /**
     * dp[w][t] = max(tasks[t] + dp[w][t - 1], dp[other w][t])
     * dp[w][t] represent the best time that can be achieved for worker w with t number of tasks.
     *
     * dp[w][t] = max(tasks[t] + dp[w][t - 1], dp[w][t - 1])
     */

    public List<List<Integer>> assign(int[] tasks, int k) {
        Result result = new Result();
        List<List<Integer>> candidate = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            candidate.add(new ArrayList<>());
        }

        helper(tasks, 0, k, candidate, result);

        return result.distribution;
    }

    private void helper(int[] tasks, int start, int k, List<List<Integer>> candidate, Result result) {
        if (start >= tasks.length) {
            int timeNeeded = shortestTime(candidate);
            if (timeNeeded < result.min) {
                result.min = timeNeeded;
                result.distribution = duplicate(candidate);
            }

            return;
        }

        for (int i = 0; i < k; i++) {
            candidate.get(i).add(tasks[start]);
            helper(tasks, start + 1, k, candidate, result);
            candidate.get(i).remove(candidate.get(i).size() - 1);
        }
    }

    private int shortestTime(List<List<Integer>> candidate) {
        int min = 0;
        for (List<Integer> list : candidate) {
            int total = 0;
            for (Integer job : list) {
                total += job;
            }

            min = Math.max(min, total);
        }

        return min;
    }

    private List<List<Integer>> duplicate(List<List<Integer>> candidate) {
        List<List<Integer>> copy = new ArrayList<>(candidate.size());
        for (List<Integer> workerJobs : candidate) {
            copy.add(new ArrayList<>(workerJobs));
        }

        return copy;
    }

    public static void main(String[] args) {
        JobAssignment sol = new JobAssignment();

        List<List<Integer>> result = sol.assign(new int[]{9, 8, 7, 4, 4}, 2);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
}
