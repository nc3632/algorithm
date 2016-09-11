import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can
 * keep "shifting" which forms the sequence:
 *
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting
 * sequence.
 *
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Return:
 *
 * [
 *  ["abc","bcd","xyz"],
 *  ["az","ba"],
 *  ["acef"],
 *  ["a","z"]
 * ]
 * Note: For the return value, each inner list's elements must follow the lexicographic order.
 */

public class GroupShifting {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                sb.append((s.charAt(i) + 26 - s.charAt(0)) % 26 + " ");
            }
            String key = sb.toString().trim();

            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);

            map.put(key, list);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : map.values()) {
            Collections.sort(list);
            result.add(list);
        }

        return result;
    }

    public static void main(String[] args) {
        GroupShifting sol = new GroupShifting();
        System.out.println(sol.groupStrings(new String[] {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
    }
}

