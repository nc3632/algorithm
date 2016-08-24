/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example:
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 *
 * BIT[] as a binary tree:
 *            ______________*
 *            ______*
 *            __*     __*
 *            *   *   *   *
 * indices: 0 1 2 3 4 5 6 7 8
 */

public class RangeSumQueryMutableBIT {
    private int[] nums;
    private int[] BIT;

    public RangeSumQueryMutableBIT(int[] nums) {
        this.nums = nums;
        BIT = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            init(i, nums[i]);
        }
    }

    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;

        init(i, diff);
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }

    private void init(int i, int val) {
        int j = i + 1;
        while (j <= nums.length) {
            BIT[j] += val;
            j += (j & -j);
        }
    }

    private int getSum(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += BIT[i];
            i -= (i & -i);
        }
        return sum;
    }

    public static void main(String[] args) {
        RangeSumQueryMutableBIT sol = new RangeSumQueryMutableBIT(new int[]{1, 3, 5});
        System.out.println(sol.sumRange(0, 0));
        System.out.println(sol.sumRange(0, 1));
        System.out.println(sol.sumRange(0, 2));
        System.out.println(sol.sumRange(1, 1));
        System.out.println(sol.sumRange(1, 2));
        System.out.println(sol.sumRange(2, 2));

        sol.update(1, 2);
        System.out.println(sol.sumRange(0, 2));
    }
}
