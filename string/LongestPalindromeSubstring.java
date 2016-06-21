/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 *
 * Example
 * Given the string = "abcdzdcab", return "cdzdc".
 */

public class LongestPalindromeSubstring {
    public String longestPalindrome(String s) {
        int max = 0, maxStart = 0, maxEnd = 0;
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; ) {
            // For each character in this string, extend the palindrome substring

            // First find the range of characters the same as s.charAt(i)
            int start = i - 1, end = i + 1;
            while (end < arr.length && arr[end] == arr[i]) {
                end++;
            }
            i = end;

            // Extend the palindrome substring
            while (start >= 0 && end < arr.length && arr[start] == arr[end]) {
                start--;
                end++;
            }

            if (max < end - start + 1) {
                max = end - start + 1;
                maxStart = start + 1;
                maxEnd = end - 1;
            }
        }

        return s.substring(maxStart, maxEnd + 1);
    }

    public static void main(String[] args) {
        LongestPalindromeSubstring sol = new LongestPalindromeSubstring();
        System.out.println(sol.longestPalindrome("abcdzdcab"));
    }
}
