import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty
 * substring in str.
 *
 * Examples:
 *
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 */

public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> wordsAppeared = new HashSet<>();

        return match(pattern, str, 0, 0, map, wordsAppeared);
    }

    private boolean match(String pattern, String str, int patternIdx, int strPos, Map<Character, String> map,
                          Set<String> wordsAppeared) {

        if (patternIdx >= pattern.length()) {
            return true;
        }

        int maxWordLen = str.length() - strPos - pattern.length() + patternIdx + 1;
        char ch = pattern.charAt(patternIdx);
        for (int i = 0; i < maxWordLen; i++) {
            String word = str.substring(strPos, strPos + i + 1);

            if (!map.containsKey(ch) && !wordsAppeared.contains(word)) {
                map.put(ch, word);
                wordsAppeared.add(word);

                if (match(pattern, str, patternIdx + 1, strPos + i + 1, map, wordsAppeared)) {
                    return true;
                }

                map.remove(ch);
                wordsAppeared.remove(word);
            } else if (map.containsKey(ch) && map.get(ch).equals(word)){
                if (match(pattern, str, patternIdx + 1, strPos + i + 1, map, wordsAppeared)) {
                    return true;
                }
            } else if (map.containsKey(ch) && !map.get(ch).startsWith(word)) {
                break;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        WordPatternII sol = new WordPatternII();
        System.out.println(sol.wordPatternMatch("abab", "redblueredblue"));
        System.out.println(sol.wordPatternMatch("aaaa", "asdasdasdasd"));
        System.out.println(sol.wordPatternMatch("aabb", "xyzabcxzyabc"));
    }
}
