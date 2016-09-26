import java.util.HashMap;
import java.util.Map;

/**
 * Task execution time 1. Needs to cool down for x time units before the same task execute again.
 * Question is the minimum execute time
 *
 * For example,
 * Task: 12323, and cool down is 3. The execute sequence is 1 2 3 _ _ 2 3, min value is 7.
 * Task: 1242353, and cool down is 4. The Execute sequence is 1 2 4 _ _ _ 2 3 5 _ _ _ 3, min value is 13
 */

public class TaskExecutionTime {
    public int executeTime(String sequence, int cooldown) {
        Map<Character, Integer> map = new HashMap<>();
        int time = 0;
        for (int i = 0; i < sequence.length(); i++, time++) {
            char ch = sequence.charAt(i);
            if (map.containsKey(ch)) {
                int diff = time - map.get(ch);
                if (diff <= cooldown) {
                    time += cooldown - diff + 1;
                }
                map.put(ch, time);
            } else {
                map.put(ch, time);
            }
        }

        return time;
    }

    public static void main(String[] args) {
        TaskExecutionTime sol = new TaskExecutionTime();
        System.out.println(sol.executeTime("12323", 3));
        System.out.println(sol.executeTime("1242353", 4));
    }
}
