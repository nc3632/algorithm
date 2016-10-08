import java.util.ArrayList;
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

        intervals.sort((i1, i2) -> i1.start - i2.start);

        List<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));
        Interval curr = result.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);
            if (curr.end < next.start) {
                result.add(next);
                curr = next;
            } else {
                curr.end = Math.max(curr.end, next.end);
            }
        }

        return result;
    }
}
