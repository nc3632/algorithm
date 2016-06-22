/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 */

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int leftMax = height[left], rightMax = height[right];
            int area = Math.min(leftMax, rightMax) * (right - left);
            max = Math.max(max, area);

            if (leftMax <= rightMax) {
                while (left < right && height[left] <= leftMax) {
                    left++;
                }
            } else {
                while (left < right && height[right] <= rightMax) {
                    right--;
                }
            }
        }

        return max;
    }
}
