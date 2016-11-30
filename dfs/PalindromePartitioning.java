import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * For example, given s = "aab",
 * Return
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */

public class PalindromePartitioning {
    public boolean isPalinedrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }
        return true;
    }

    public void partition(String s, int start, List<String> candidate, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(candidate));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (isPalinedrome(s, start, i)) {
                candidate.add(s.substring(start, i + 1));
                partition(s, i + 1, candidate, result);
                candidate.remove(candidate.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> candidate = new ArrayList<>();
        partition(s, 0, candidate, result);

        return result;
    }

    public static void main(String[] args) {
        PalindromePartitioning sol = new PalindromePartitioning();
        List<List<String>> result = sol.partition("aab");
        for (List<String> list : result) {
            System.out.println(list);
        }
    }
}
