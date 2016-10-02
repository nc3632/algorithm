import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum
 * number of conference rooms required.
 *
 * For example,
 * [
 *   [0, 3],
 *   [0, 7],
 *   [0, 3],
 *   [4, 7],
 *   [4, 10],
 *   [8, 11],
 *   [8, 11],
 *   [12, 15],
 *   [12, 15],
 *   [10, 15]
 * ]
 *
 * needs 3 meeting rooms
 */

public class MeetingRoomII {
    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        queue.offer(intervals[0]);

        int count = 1;
        for (Interval interval : intervals) {
            if (interval.start >= queue.peek().end) {
                queue.poll();
            } else {
                count++;
            }
            queue.offer(interval);
        }

        return count;
    }

    public static void main(String[] args) {
        MeetingRoomII sol = new MeetingRoomII();
        System.out.println(sol.minMeetingRooms(new Interval[]{
                new Interval(0, 3),
                new Interval(0, 7),
                new Interval(4, 7),
                new Interval(4, 10),
                new Interval(8, 11),
                new Interval(8, 11),
                new Interval(12, 15),
                new Interval(12, 15),
                new Interval(10, 15)
        }));
    }
}
