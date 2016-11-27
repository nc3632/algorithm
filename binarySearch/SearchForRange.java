/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */

public class SearchForRange {
    public int[] searchRange(int[] nums, int target) {
        int firstPos = find(nums, 0, nums.length - 1, target, true);
        if (firstPos == -1) {
            return new int[] {-1, -1};
        } else {
            int lastPos = find(nums, firstPos, nums.length - 1, target, false);
            return new int[] {firstPos, lastPos};
        }
    }

    private int find(int[] nums, int start, int end, int target, boolean findFirst) {
        int candidate = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                candidate = mid;
                if (findFirst) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return candidate;
    }
}
