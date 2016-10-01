import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of jobs where every job has a deadline and associated profit if the job is finished before the
 * deadline. It is also given that every job takes single unit of time, so the minimum possible deadline for any job
 * is 1. How to maximize total profit if only one job can be scheduled at a time.
 *
 * Examples:
 *
 * Input: Four Jobs with following deadlines and profits
 * JobID    Deadline      Profit
 *   a        4            20
 *   b        1            10
 *   c        1            40
 *   d        1            30
 * Output: Following is maximum profit sequence of jobs
 *   c, a
 *
 * Input:  Five Jobs with following deadlines and profits
 * JobID     Deadline     Profit
 *   a         2           100
 *   b         1           19
 *   c         2           27
 *   d         1           25
 *   e         3           15
 * Output: Following is maximum profit sequence of jobs
 *   c, a, e
 */

public class JobSequence {
    private static class Job {
        char id;
        int deadline;
        int profit;

        public Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public List<Character> findJobSequence(Job[] jobs) {
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o2.profit - o1.profit;
            }
        });

        List<Character> sequence = new ArrayList<>();
        boolean[] used = new boolean[findMaxDealine(jobs)];
        for (Job job : jobs) {
            int pos = job.deadline - 1;
            while (pos >= 0 && used[pos]) {
                pos--;
            }

            if (pos >= 0) {
                used[pos] = true;
                sequence.add(job.id);
            }
        }

        return sequence;
    }

    private int findMaxDealine(Job[] jobs) {
        int max = Integer.MIN_VALUE;
        for (Job job : jobs) {
            max = Math.max(max, job.deadline);
        }

        return max;
    }

    public static void main(String[] args) {
        JobSequence sol = new JobSequence();
        System.out.println(sol.findJobSequence(new Job[]{
                new Job('a', 4, 20),
                new Job('b', 1, 10),
                new Job('c', 1, 40),
                new Job('d', 1, 30)
        }));
        System.out.println(sol.findJobSequence(new Job[]{
                new Job('a', 2, 100),
                new Job('b', 1, 19),
                new Job('c', 2, 27),
                new Job('d', 1, 25),
                new Job('e', 3, 15)
        }));
    }
}
