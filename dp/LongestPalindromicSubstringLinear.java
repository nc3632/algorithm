/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 */
public class LongestPalindromicSubstringLinear {
    public String longestPalindrome(String s) {
        // Add boundaries
        char[] arr = addBoundaries(s);

        // rightBorder represent the range of palindromic substring
        // centered at arr[i]
        int rightBorder = 0, center = 0;

        // dp represents HALF of the length of the longest palindromic substring
        // centered at arr[i];
        int[] dp = new int[arr.length];

        // Find the longest palindromic substring centered at each character of arr
        for (int i = 1; i < arr.length; i++) {
            // Find the mirroring position of i around the center
            int mirrorPos = center - (i - center);

            // Instead of expanding the palindromic substring one by one,
            // we can take advantage of dp[mirrorPos]
            dp[i] = (i > rightBorder) ? 0 : Math.min(rightBorder - i, dp[mirrorPos]);

            // See if we can expand this palindromic substring more
            while (i - dp[i] > 0 && i + dp[i] + 1 < arr.length && arr[i + dp[i] + 1] == arr[i - dp[i] - 1]) {
                dp[i]++;
            }

            // If the current palindromic substring goes beyond the previous
            // palindromic substring, we will need to adjust the center and range
            if (i + dp[i] > rightBorder) {
                center = i;
                rightBorder = i + dp[i];
            }
        }

        // Walk through dp, and find the longest one
        int maxLength = 0, bestCenter = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                bestCenter = i;
            }
        }

        // Get the long palindromic substring
        return removeBoundaries(arr, bestCenter, maxLength);
    }

    /**
     * Add boundaries to each character of string s.
     * For example, "abc" will be converted to "|a|b|c|"
     *
     * The reason of adding such a boundary is to make it
     * easier to handle a string with either even or odd number of character.
     * Note that we will need to mirror the left and right parts around
     * some center.
     */
    private char[] addBoundaries(String s) {
        char[] arr = new char[2 * s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            arr[2 * i] = '|';
            arr[2 * i + 1] = s.charAt(i);
        }
        arr[2 * s.length()] = '|';

        return arr;
    }

    private String removeBoundaries(char[] arr, int center, int maxLength) {
        StringBuffer sb = new StringBuffer();
        for (int i = center - maxLength; i <= center + maxLength; i++) {
            if (arr[i] != '|') {
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }
}
