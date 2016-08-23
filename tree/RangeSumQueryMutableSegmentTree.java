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
 */

public class RangeSumQueryMutableSegmentTree {
    private int[] st;
    private int n;

    public RangeSumQueryMutableSegmentTree(int[] nums) {
        n = nums.length;

        if (n != 0) {
            int height = (int) Math.ceil(Math.log(nums.length) / Math.log(2));
            int size = 2 * (int) Math.pow(2, height) - 1;
            st = new int[size];

            buildSegmentTree(nums, 0, nums.length - 1, 0);
        }
    }

    public void update(int i, int val) {
        updateSegmentTree(0, n - 1, i, val, 0);
    }

    public int sumRange(int i, int j) {
        return searchSegmentTree(0, n - 1, i, j, 0);
    }

    private int buildSegmentTree(int[] nums, int start, int end, int rootIndex) {
        if (start == end) {
            st[rootIndex] = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            st[rootIndex] = buildSegmentTree(nums, start, mid, 2 * rootIndex + 1)
                    + buildSegmentTree(nums, mid + 1, end, 2 * rootIndex + 2);
        }

        return st[rootIndex];
    }

    private int searchSegmentTree(int start, int end, int i, int j, int rootIndex) {
        if (i <= start && j >= end) {
            return st[rootIndex];
        } else if (j < start || i > end) {
            return 0;
        } else {
            int mid = start + (end - start) / 2;
            return searchSegmentTree(start, mid, i, j, 2 * rootIndex + 1)
                    + searchSegmentTree(mid + 1, end, i, j, 2 * rootIndex + 2);
        }
    }

    private int updateSegmentTree(int start, int end, int i, int val, int rootIndex) {
        if (start == end && start == i) {
            st[rootIndex] = val;
        } else if (start <= i && i <= end) {
            int mid = start + (end - start) / 2;
            st[rootIndex] = updateSegmentTree(start, mid, i, val, 2 * rootIndex + 1)
                    + updateSegmentTree(mid + 1, end, i, val, 2 * rootIndex + 2);
        }

        return st[rootIndex];
    }

    public static void main(String[] args) {
        RangeSumQueryMutableSegmentTree sol = new RangeSumQueryMutableSegmentTree(new int[]{1, 3, 5});
        System.out.println(sol.sumRange(0, 0));
        System.out.println(sol.sumRange(0, 1));
        System.out.println(sol.sumRange(0, 2));
        System.out.println(sol.sumRange(1, 1));
        System.out.println(sol.sumRange(1, 2));
        System.out.println(sol.sumRange(2, 2));
    }
}
