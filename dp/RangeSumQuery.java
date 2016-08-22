/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 */

public class RangeSumQuery {
    private int[] sum;

    public RangeSumQuery(int[] nums) {
        sum = new int[nums.length + 1];
        indexing(nums);
    }

    public int sumRange(int i, int j) {
        return sum[i] - sum[j + 1];
    }

    private void indexing(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            sum[i] += sum[i + 1] + nums[i];
        }
    }

    public static void main(String[] args) {
        RangeSumQuery sol = new RangeSumQuery(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(sol.sumRange(0, 2));
        System.out.println(sol.sumRange(2, 5));
        System.out.println(sol.sumRange(0, 5));
    }
}
