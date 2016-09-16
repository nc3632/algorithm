import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */

public class MergeInterval {
    private static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                int start1 = ((Interval) o1).start;
                int start2 = ((Interval) o2).start;

                if (start1 < start2) {
                    return -1;
                } else if (start1 == start2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        Interval curr = intervals.get(0);
        for (int i = 1; i < intervals.size(); ) {
            Interval next = intervals.get(i);
            if (curr.end < next.start) {
                i++;
                curr = next;
            } else {
                curr.end = Math.max(curr.end, next.end);
                intervals.remove(i);
            }
        }

        return intervals;
    }
}
