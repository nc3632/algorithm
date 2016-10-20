import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a word, find its anagrams from a dictionary.
 */

public class SearchAnagram {
    public List<String> searchAnagram(String s, List<String> dict) {
        String reference = buildTag(s);

        List<String> result = new ArrayList<>();
        for (String word : dict) {
            if (word.length() == s.length() && reference.equals(buildTag(word))) {
                result.add(word);
            }
        }

        return result;
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
        SearchAnagram sol = new SearchAnagram();
        System.out.println(sol.searchAnagram("tea", Arrays.asList("aet", "tea", "blah")));
    }
}
