import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a
 * valid dictionary word.
 *
 * Return all such possible sentences.
 *
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 *
 * A solution is ["cats and dog", "cat sand dog"].
 */

public class WordBreakII {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String>[] resolved = new ArrayList[s.length()];
        int maxWordLength = getMaxWordLength(dict);

        return helper(s, 0, dict, resolved, maxWordLength);
    }

    private int getMaxWordLength(Set<String> dict) {
        int max = 0;
        for(String s : dict) {
            max = Math.max(max, s.length());
        }

        return max;
    }

    private ArrayList<String> helper(String s,
                                     int start,
                                     Set<String> dict,
                                     ArrayList<String>[] resolved,
                                     int maxWordLength) {

        // Need to use resolved to cache the solutions starting from "start".
        // Otherwise, it would be checked again and again.
        // It might be empty, and that means that is no solution starting from it.
        if (resolved[start] != null) {
            return resolved[start];
        }

        resolved[start] = new ArrayList<>();
        for (int i = start + 1; i <= s.length() && i <= start + maxWordLength; i++) {
            String word = s.substring(start, i);
            if (dict.contains(word)) {
                if (i == s.length()) {
                    resolved[start].add(word);
                } else {
                    ArrayList<String> fromRight = helper(s, i, dict, resolved, maxWordLength);
                    for (String candidate : fromRight) {
                        resolved[start].add(word + " " + candidate);
                    }
                }
            }
        }
        return resolved[start];
    }

    public static void main(String[] args) {
        WordBreakII sol = new WordBreakII();
        Set<String> dict = new HashSet<String>() {{
            add("cat");
            add("cats");
            add("and");
            add("sand");
            add("dog");
        }};
        System.out.println(sol.wordBreak("catsanddog", dict).toString());

        dict = new HashSet<String>() {{
            add("a");
            add("aa");
            add("aaa");
            add("aaaa");
            add("aaaaa");
            add("aaaaaa");
            add("aaaaaaa");
            add("aaaaaaaa");
            add("aaaaaaaaa");
            add("aaaaaaaaaa");
        }};
        System.out.println(sol.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", dict).toString());
    }
}
