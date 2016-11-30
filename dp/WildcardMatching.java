/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 */

public class WildcardMatching {
    /**
     * Use solution[i][j] to represent if there is a match from s(1, i) to
     * p(1, j).
     *
     * solution[i][j] = solution[i - 1][j - 1] && isMatch(s(i), p(j)).
     * '*' makes things a little bit more complicated. To consider if
     * s(i) matches '*', we only need to make sure solution[i][j - 1]
     * is true or solution[i - 1][j] is true
     */
    public boolean isMatch(String s, String p) {
        boolean[][] solution = new boolean[s.length() + 1][p.length() + 1];
        solution[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            solution[0][i] = solution[0][i - 1] && p.charAt(i - 1) == '*';
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    solution[i][j] = solution[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    solution[i][j] = solution[i - 1][j] || solution[i][j - 1];
                }
            }
        }

        return solution[s.length()][p.length()];
    }
}
