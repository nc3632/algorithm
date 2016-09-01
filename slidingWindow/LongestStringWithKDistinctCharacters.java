import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * For example, Given s = “eceba” and k = 2,
 * T is "ece" which its length is 3.
 */

public class LongestStringWithKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, windowStart = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            if (map.size() == k) {
                max = Math.max(max, i - windowStart + 1);
            }

            while (map.size() > k) {
                char chToRemove = s.charAt(windowStart);
                int val = map.get(chToRemove) - 1;
                if (val == 0) {
                    map.remove(chToRemove);
                } else {
                    map.put(chToRemove, val);
                }
                windowStart++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LongestStringWithKDistinctCharacters sol = new LongestStringWithKDistinctCharacters();
        System.out.println(sol.lengthOfLongestSubstringKDistinct("eceba", 2));
    }
}
