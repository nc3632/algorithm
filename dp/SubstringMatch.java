/**
 * Given two strings s1 and s2, count how many ways that s2 is a substring of s1.
 *
 * For example, s1 = "catastca", s2 = "cat". The number of ways is 3.
 * "CATastca", "CAtasTca", and "CatAsTca".
 */

public class SubstringMatch {
    public int numMatches(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[] dp = new int[len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 1; j <= Math.min(i + 1, len2); j++) {
                if (s1.charAt(i) == s2.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + 1;
                }
            }
        }

        return dp[len2];
    }

    public static void main(String[] args) {
        SubstringMatch sol = new SubstringMatch();
        System.out.println(sol.numMatches("catastca", "cat"));
        System.out.println(sol.numMatches("cat", "catastca"));
    }
}
