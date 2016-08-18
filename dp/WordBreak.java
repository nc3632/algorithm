import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence
 * of one or more dictionary words.
 *
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 *
 * Return true because "leetcode" can be segmented as "leet code".
 */

public class WordBreak {
    public boolean wordBreak(String s, Set<String> wordDict) {
        int maxLen = getMaxWordLength(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int j = 1; j <= s.length(); j++) {
            for (int i = Math.max(1, j - maxLen); i <= j; i++) {
                if (dp[i - 1] && wordDict.contains(s.substring(i - 1, j))) {
                    dp[j] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    private int getMaxWordLength(Set<String> wordDict) {
        Iterator<String> iterator = wordDict.iterator();

        int len = 0;
        while(iterator.hasNext()) {
            len = Math.max(len, iterator.next().length());
        }

        return len;
    }

    public static void main(String[] args) {
        WordBreak sol = new WordBreak();

        Set<String> set = new HashSet<>();
        set.add("a");
        System.out.println(sol.wordBreak("a", set));
    }
}
