/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

public class PalindromePartitioningII {
    /**
     * Use cut[i, j] to represent the minimum number of cuts from index i through index j.
     * cut[i, j] = min(cut[i, k] + 1 + cut[k, j]), where i <= k < i
     * For cut[k, j], it is actually a check if s(k,j) is a palindrome.
     * Note that to check if s(k, j) is a palindrome, we only need to make sure
     * if s(k) == s(j) and s(k + 1, j - 1) is a palindrome. Since s(k + 1, j - 1) was cached
     * in previous loops, we will need to cache them instead of doing a palindrome check
     * every time.
     */
    public int minCut(String s) {
        // Cache to avoid duplicate check
        boolean[][] pal = new boolean[s.length()][s.length()];

        int[] cut = new int[s.length()];
        for (int i = 0; i < cut.length; i++) {
            cut[i] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i)
                        && (j + 1 > i - 1 // Only one character
                        || pal[j + 1][i - 1])) {

                    pal[j][i] = true;
                    cut[i] = (j == 0) ? 0 : Math.min(cut[i], cut[j - 1] + 1);
                }
            }
        }

        return cut[s.length() - 1];
    }
}
