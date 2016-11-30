/**
 * Given two strings, find the longest common subsequence (LCS).
 * Your code should return the length of LCS.
 *
 * Example
 * For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.
 * For "ABCD" and "EACB", the LCS is "AC", return 2.
 */
public class LongestCommonSubsequence {
    /**
     * Use solution[i][j] to represent the longest common subsequence
     * between A(0, i) and B(0, j).
     *
     * if A[i] == B[j] then
     *   solution[i][j] = solution[i - 1][j - 1] + 1;
     * else
     *   solution[i][j] = max(solution[i][j - 1], solution[i - 1][j])
     * end
     */
    public int longestCommonSubsequence(String A, String B) {
        int[][] solution = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    solution[i][j] = solution[i - 1][j - 1] + 1;
                } else {
                    solution[i][j] = Integer.max(solution[i][j - 1], solution[i - 1][j]);
                }
            }
        }

        return solution[A.length()][B.length()];
    }
}
