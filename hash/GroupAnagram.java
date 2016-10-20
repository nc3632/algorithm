import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings, group anagrams together.
 *
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 *
 * [
 *   ["ate", "eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note: All inputs will be in lower-case.
 */
public class GroupAnagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        Arrays.sort(strs);

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            String tag = buildTag(s);

            if (!map.containsKey(tag)) {
                map.put(tag, new LinkedList<>());
            }
            map.get(tag).add(s);
        }

        return new LinkedList<>(map.values());
    }

    private String buildTag(String s) {
        char[] map = new char[26];
        Arrays.fill(map, '0');

        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }

        return String.valueOf(map);
    }

    public static void main(String[] args) {
        GroupAnagram sol = new GroupAnagram();
        List<List<String>> result = sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> list : result) {
            System.out.println(list);
        }
    }
}
