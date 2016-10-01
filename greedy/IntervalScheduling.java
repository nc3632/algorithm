import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Job j starts at sj and finishes at fj.
 * Two jobs compatible if they don't overlap.
 * Goal: find maximum subset of mutually compatible jobs.
 *
 * For example, jobs are
 * 'a', 0, 6
 * 'b', 1, 4
 * 'c', 3, 5
 * 'd', 3, 8
 * 'e', 4, 7
 * 'f', 5, 9
 * 'g', 6, 10
 * 'h', 8, 11
 *
 * output should be
 * 'b', 'e', 'h'
 */
public class IntervalScheduling {
    private static class Job {
        char id;
        int start;
        int end;

        public Job(char id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }

    public List<Character> findSequence(Job[] jobs) {
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.end - o2.end;
            }
        });

        int earliestTime = 0;
        List<Character> sequence = new ArrayList<>();
        for (Job job : jobs) {
            if (job.start >= earliestTime) {
                sequence.add(job.id);
                earliestTime = job.end;
            }
        }

        return sequence;
    }

    public static void main(String[] args) {
        IntervalScheduling sol = new IntervalScheduling();
        System.out.println(sol.findSequence(new Job[]{
                new Job('a', 0, 6),
                new Job('b', 1, 4),
                new Job('c', 3, 5),
                new Job('d', 3, 8),
                new Job('e', 4, 7),
                new Job('f', 5, 9),
                new Job('g', 6, 10),
                new Job('h', 8, 11)
        }));

    }
}
