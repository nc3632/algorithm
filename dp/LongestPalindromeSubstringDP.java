/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 *
 * Example
 * Given the string = "abcdzdcab", return "cdzdc".
 */

public class LongestPalindromeSubstringDP {
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int start = 0;
        int maxLen = 1;

        // Palindromic substring with length of 1
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }

        // Palindromic substring with length of 2
        for (int i = 0; i < dp.length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            }
        }

        // Palindromic substring with length of more than 2
        for (int k = 3; k <= dp.length; k++) {
            for (int i = 0; i < dp.length - k + 1; i++) {
                int j = i + k - 1;
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    start = i;
                    maxLen = k;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        LongestPalindromeSubstringDP sol = new LongestPalindromeSubstringDP();
        System.out.println(sol.longestPalindrome("ccc"));
    }
}
