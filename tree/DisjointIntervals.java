import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a
 * list of disjoint intervals.
 *
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 *
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 */

public class DisjointIntervals {
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

    // In this tree map, the key is the lower bound of the interval
    private TreeMap<Integer, Interval> treeMap = new TreeMap<>();

    public DisjointIntervals() {
    }

    public void addNum(int val) {
        if (treeMap.containsKey(val)) {
            return;
        }

        Map.Entry<Integer, Interval> lowerEntry = treeMap.lowerEntry(val);
        Map.Entry<Integer, Interval> higherEntry = treeMap.higherEntry(val);

        if (lowerEntry != null && higherEntry != null
                && lowerEntry.getValue().end + 1 == val && val + 1 == higherEntry.getKey()) {
            lowerEntry.getValue().end = higherEntry.getValue().end;
            treeMap.remove(higherEntry.getKey());
        } else if (higherEntry != null && val + 1 == higherEntry.getKey()) {
            treeMap.put(val, new Interval(val, higherEntry.getValue().end));
            treeMap.remove(higherEntry.getKey());
        } else if (lowerEntry != null && lowerEntry.getValue().end >= val - 1) {
            lowerEntry.getValue().end = Math.max(val, lowerEntry.getValue().end);
        } else {
            treeMap.put(val, new Interval(val, val));
        }
    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(treeMap.values());
    }

}
