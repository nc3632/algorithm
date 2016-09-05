import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int n : nums) {
            if (!map.containsKey(n)) {
                int leftSize = map.getOrDefault(n - 1, 0);
                int rightSize = map.getOrDefault(n + 1, 0);
                int size = leftSize + rightSize + 1;

                map.put(n, size);
                map.put(n - leftSize, size);
                map.put(n + rightSize, size);

                max = Math.max(max, size);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence sol = new LongestConsecutiveSequence();
        System.out.println(sol.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(sol.longestConsecutive(new int[]{1, 2, 0, 1}));
        System.out.println(sol.longestConsecutive(new int[]{4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3}));
    }
}
