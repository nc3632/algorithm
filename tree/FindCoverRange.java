import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a
 * list of disjoint intervals.
 */

public class FindCoverRange {
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

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    TreeMap<Integer, Interval> treeMap = new TreeMap<>();

    public void addInterval(Interval interval) {
        // Merge the newly incoming interval with the first interval in front of it.
        Map.Entry<Integer, Interval> prevEntry = treeMap.lowerEntry(interval.start);
        if (prevEntry == null || prevEntry.getValue().end < interval.start) {
            treeMap.put(interval.start, interval);
            prevEntry = treeMap.floorEntry(interval.start);
        } else if (prevEntry.getValue().end >= interval.start) {
            prevEntry.getValue().end = Math.max(prevEntry.getValue().end, interval.end);
        }

        // See if it can be merged with following entries
        Map.Entry<Integer, Interval> currEntry = treeMap.higherEntry(prevEntry.getKey());
        while (currEntry != null && currEntry.getValue().start <= prevEntry.getValue().end) {
            prevEntry.getValue().end = Math.max(prevEntry.getValue().end, currEntry.getValue().end);
            treeMap.remove(currEntry.getKey());

            prevEntry = currEntry;
            currEntry = treeMap.higherEntry(prevEntry.getKey());
        }
    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(treeMap.values());
    }

    public static void main(String[] args) {
        FindCoverRange sol = new FindCoverRange();

        sol.addInterval(new Interval(1, 3));
        System.out.println(sol.getIntervals().toString());

        sol.addInterval(new Interval(7, 8));
        System.out.println(sol.getIntervals().toString());

        sol.addInterval(new Interval(2, 4));
        System.out.println(sol.getIntervals().toString());

        sol.addInterval(new Interval(4, 10));
        System.out.println(sol.getIntervals().toString());

        sol.addInterval(new Interval(5, 15));
        System.out.println(sol.getIntervals().toString());
    }
}
