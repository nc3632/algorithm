import java.util.ArrayList;
import java.util.List;

/**
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints
 * all occurrences of pat[] and its permutations (or anagrams) in txt[]. You may assume that n > m.
 * Expected time complexity is O(n)
 *
 * Examples:
 *
 * 1) Input:  txt[] = "BACDGABCDA"  pat[] = "ABCD"
 * Output:   Found at Index 0
 * Found at Index 5
 * Found at Index 6
 * 2) Input: txt[] =  "AAABABAA" pat[] = "AABA"
 * Output:   Found at Index 0
 * Found at Index 1
 * Found at Index 4
 */

public class AnagramSubstring {
    private static int MAP_SIZE = 26;

    public List<String> search(String pattern, String txt) {
        int windowSize = pattern.length();

        int[] patternCount = buildCountingMap(pattern);
        int[] windowCount = buildCountingMap(txt.substring(0, windowSize));

        List<String> result = new ArrayList<>();
        if (windowMatchPattern(patternCount, windowCount)) {
            result.add(txt.substring(0, windowSize));
        }

        for (int i = 1; i < txt.length() - windowSize + 1; i++) {
            int windowEnd = i + windowSize - 1;
            updateWindowMap(windowCount, txt.charAt(i - 1), txt.charAt(windowEnd));

            if (windowMatchPattern(patternCount, windowCount)) {
                result.add(txt.substring(i, windowEnd + 1));
            }
        }

        return result;
    }

    private int[] buildCountingMap(String str) {
        int[] map = new int[MAP_SIZE];
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'A';
            map[idx]++;
        }

        return map;
    }

    private boolean windowMatchPattern(int[] patternCount, int[] windowCount) {
        for (int i = 0; i < MAP_SIZE; i++) {
            if (patternCount[i] != windowCount[i]) {
                return false;
            }
        }

        return true;
    }

    private void updateWindowMap(int[] windowCount, char toRemove, char toAdd) {
        windowCount[toRemove - 'A']--;
        windowCount[toAdd - 'A']++;
    }

    public static void main(String[] args) {
        AnagramSubstring sol = new AnagramSubstring();
        System.out.println(sol.search("ABCD", "BACDGABCDA"));
        System.out.println(sol.search("AABA", "AAABABAA"));
    }
}
