/**
 * Given two strings, find the longest common substring.
 * Return the length of it.
 *
 * Example
 * Given A = "ABCD", B = "CBCE", return 2.
 */

public class LongestCommonSubstring {
    /**
     * Use solution[i][j] to represent the longest common substring between A(0 -> i) and B(0 -> j).
     *
     * if A[i] == B[j], then solution[i][j] = solution[i - 1][j - 1] + 1
     *
     * Use result to keep the biggest solution[i][j] value.
     */
    public int longestCommonSubstring(String A, String B) {
        int[][] solution = new int[A.length() + 1][B.length() + 1];
        int result = 0;
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    solution[i][j] = solution[i - 1][j - 1] + 1;
                    result = Integer.max(result, solution[i][j]);
                }
            }
        }

        return result;
    }
}
