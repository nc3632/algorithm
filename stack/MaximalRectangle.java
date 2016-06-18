import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing all ones and return its area.
 */

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int max = 0;
        int[] heights = new int[matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            // The heights of each column from current row's point of view
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] = heights[j] + 1;
                } else {
                    heights[j] = 0;
                }
            }

            // Get the maximal rectangle that can be obtained from current row's
            // point of view, and then pick the maximal one
            max = Math.max(max, getRowMax(heights));
        }

        return max;
    }

    /**
     * Traditional maximal rectangle in histogram problem.
     *
     * @param heights
     * @return
     */
    private int getRowMax(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                max = Math.max(max, heights[stack.pop()] * (i - (stack.isEmpty() ? 0 : stack.peek() + 1)));
            }
            stack.push(i);
        }

        return max;
    }
}
