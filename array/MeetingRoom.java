import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings. For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 */

public class MeetingRoom {
    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1].end > intervals[i].start) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MeetingRoom sol = new MeetingRoom();
        System.out.println(sol.canAttendMeetings(new Interval[]{
                new Interval(0, 30),
                new Interval(5, 10),
                new Interval(15, 20)
        }));
        System.out.println(sol.canAttendMeetings(new Interval[]{
                new Interval(0, 30),
                new Interval(30, 50)
        }));
    }
}
