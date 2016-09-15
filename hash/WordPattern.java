import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in
 * str.
 *
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single
 * space.
 */

public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character, Integer> patternMap = new HashMap<>();
        Map<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            // If the pattern previously maps to is different from words,
            // then there is mismatch between patterns and words.
            Integer pos1 = patternMap.put(pattern.charAt(i), i);
            Integer pos2 = wordMap.put(words[i], i);

            if (pos1 == null && pos2 != null
                    || pos1 != null && pos2 == null
                    || pos1 != null && pos2 != null && !pos1.equals(pos2)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        WordPattern sol = new WordPattern();
        System.out.println(sol.wordPattern("abba", "dog cat cat dog"));
        System.out.println(sol.wordPattern("abba", "dog cat cat fish"));
        System.out.println(sol.wordPattern("aaaa", "dog cat cat dog"));
        System.out.println(sol.wordPattern("abba", "dog dog dog dog"));
    }
}
