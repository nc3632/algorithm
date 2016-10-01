import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least
 * distance k from each other.
 *
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty
 * string "".
 *
 * Example 1:
 * str = "aabbcc", k = 3
 *
 * Result: "abcabc"
 *
 * The same letters are at least distance 3 from each other.
 * Example 2:
 * str = "aaabc", k = 3
 *
 * Answer: ""
 *
 * It is not possible to rearrange the string.
 * Example 3:
 * str = "aaadbbcc", k = 2
 *
 * Answer: "abacabcd"
 *
 * Another possible answer is: "abcabcda"
 *
 * The same letters are at least distance 2 from each other.
 */

public class ReorderKDistance {
    public String rearrangeString(String str, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        queue.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();
        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> entry = queue.poll();
            sb.append(entry.getKey());
            waitQueue.offer(entry);
            entry.setValue(entry.getValue() - 1);

            if (waitQueue.size() < k) {
                continue;
            }

            entry = waitQueue.poll();
            if (entry.getValue() > 0) {
                queue.offer(entry);
            }
        }

        return (sb.length() != str.length()) ? "" : sb.toString();
    }

    public static void main(String[] args) {
        ReorderKDistance sol = new ReorderKDistance();
        System.out.println(sol.rearrangeString("aabbcc", 3));
        System.out.println(sol.rearrangeString("aaabc", 3));
        System.out.println(sol.rearrangeString("aaadbbcc", 2));
    }
}
