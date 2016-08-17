import java.util.Arrays;

/**
 * Given a sequence of words, and a limit on the number of characters that can be put in one line (line width). Put
 * line breaks in the given sequence such that the lines are printed neatly. Assume that the length of each word is
 * smaller than the line width.
 *
 * The word processors like MS Word do task of placing line breaks. The idea is to have balanced lines. In other
 * words, not have few lines with lots of extra spaces and some lines with small amount of extra spaces.
 *
 * The extra spaces includes spaces put at the end of every line except the last one.
 * The problem is to minimize the following total cost.
 * Cost of a line = (Number of extra spaces in the line)^3
 * Total Cost = Sum of costs for all lines
 *
 * For example, consider the following string and line width M = 15
 * "Geeks for Geeks presents word wrap problem"
 *
 * Following is the optimized arrangement of words in 3 lines
 * Geeks for Geeks
 * presents word
 * wrap problem
 *
 * The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3 respectively.
 * So optimal value of total cost is 0 + 2*2 + 3*3 = 13
 * Please note that the total cost function is not sum of extra spaces, but sum of cubes (or square is also used) of
 * extra spaces. The idea behind this cost function is to balance the spaces among lines. For example, consider the
 * following two arrangement of same set of words:
 *
 * 1) There are 3 lines. One line has 3 extra spaces and all other lines have 0 extra spaces. Total extra spaces = 3 +
 *  0 + 0 = 3. Total cost = 3*3*3 + 0*0*0 + 0*0*0 = 27.
 *
 * 2) There are 3 lines. Each of the 3 lines has one extra space. Total extra spaces = 1 + 1 + 1 = 3. Total cost =
 * 1*1*1 + 1*1*1 + 1*1*1 = 3.
 *
 * Total extra spaces are 3 in both scenarios, but second arrangement should be preferred because extra spaces are
 * balanced in all three lines. The cost function with cubic sum serves the purpose because the value of total cost
 * in second scenario is less.
 */

public class WordWrap {
    public int solveWordWrap (int length[], int n, int maxWidth) {
        // The cost of putting words i -> j in one line
        int[][] lineCost = new int[n + 1][n + 1];
        for (int i = 0; i < lineCost.length; i++) {
            Arrays.fill(lineCost[i], Integer.MAX_VALUE);
        }

        // Calculate the cost of putting words i -> j in one line
        for (int i = 1; i <= n; i++) {
            int extraSpaces = maxWidth - length[i - 1];
            for (int j = i; j <= n && extraSpaces >= 0; j++) {
                lineCost[i][j] = extraSpaces * extraSpaces;
                if (j != n) {
                    extraSpaces -= length[j] + 1;
                }
            }
        }

        // Calculate the minimum cost of wrapping words from 1 -> n
        // That is keep trying from 1 -> n and see what is the best way
        // to wrap the words.
        int[] cost = new int[n + 1];
        cost[0] = 0;
        for (int j = 1; j <= n; j++) {
            cost[j] = Integer.MAX_VALUE;
            for (int i = 1; i <= j; i++) {
                // See whether we want to put word i to the previous line or a new line
                if (cost[i - 1] != Integer.MAX_VALUE
                        && lineCost[i][j] != Integer.MAX_VALUE
                        && (cost[i - 1] + lineCost[i][j] < cost[j])) {
                    cost[j] = cost[i - 1] + lineCost[i][j];
                }
            }
        }

        return cost[n];
    }

    public static void main(String[] args) {
        WordWrap sol = new WordWrap();

        int[] wordLengths = new int[] {5, 3, 5, 8, 4, 4, 7};
        int maxWidth = 15;
        System.out.println(sol.solveWordWrap(wordLengths, wordLengths.length, maxWidth));
    }
}
