import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
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

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int ref1 = determineInsertPos(intervals, 0, intervals.size() - 1, newInterval.start);
        int ref2 = determineInsertPos(intervals, 0, intervals.size() - 1, newInterval.end);

        Interval mergedInterval = new Interval();
        mergedInterval.start = (ref1 != -1 && overlap(intervals.get(ref1), newInterval))
                ? Math.min(intervals.get(ref1).start, newInterval.start)
                : newInterval.start;
        mergedInterval.end = (ref2 != -1 && overlap(intervals.get(ref2), newInterval))
                ? Math.max(intervals.get(ref2).end, newInterval.end)
                : newInterval.end;

        int deleteStart = (ref1 != -1 && overlap(intervals.get(ref1), newInterval)) ? ref1 : ref1 + 1;
        int deleteEnd = ref2;

        for (int i = deleteStart; i <= deleteEnd; i++) {
            intervals.remove(deleteStart);
        }
        intervals.add(deleteStart, mergedInterval);

        return intervals;
    }

    private int determineInsertPos(List<Interval> intervals, int start, int end, int val) {
        int candidate = -1;
        for (int i = start, j = end; i <= j; ) {
            int mid = i + (j - i) / 2;

            if (intervals.get(mid).start == val) {
                candidate = mid;
                break;
            } else if (intervals.get(mid).start < val) {
                candidate = mid;
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        return candidate;
    }

    private boolean overlap(Interval interval1, Interval interval2) {
        return !(interval1.start > interval2.end || interval1.end < interval2.start);
    }

    public static void main(String[] args) {
        InsertInterval sol = new InsertInterval();

        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(6, 9));
        intervals = sol.insert(intervals, new Interval(2, 5));

        for (Interval interval : intervals) {
            System.out.print(interval.start + ":" + interval.end + " ");
        }
        System.out.println();

        intervals.clear();
        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(12, 16));
        intervals = sol.insert(intervals, new Interval(4, 9));

        for (Interval interval : intervals) {
            System.out.print(interval.start + ":" + interval.end + " ");
        }
        System.out.println();

        intervals.clear();
        intervals = sol.insert(intervals, new Interval(5, 7));

        for (Interval interval : intervals) {
            System.out.print(interval.start + ":" + interval.end + " ");
        }
        System.out.println();

        intervals.clear();
        intervals.add(new Interval(1, 5));
        intervals = sol.insert(intervals, new Interval(0, 3));

        for (Interval interval : intervals) {
            System.out.print(interval.start + ":" + interval.end + " ");
        }
        System.out.println();

        intervals.clear();
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(5, 7));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(11, 13));
        intervals = sol.insert(intervals, new Interval(3, 6));

        for (Interval interval : intervals) {
            System.out.print(interval.start + ":" + interval.end + " ");
        }
        System.out.println();
    }
}
