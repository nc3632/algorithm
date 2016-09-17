/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Note:
 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.
 */

public class FindSingleDuplicate {
    public int findDuplicate(int[] nums) {
        // As the values of the numbers won't get out of the range of the integer array,
        // Finding the duplicate number is the same as finding a loop in a linked list
        // For example, nums = {1, 3, 2, 4, 5, 2} is like a linked list
        // 1 -> 3 -> 4 -> 5 -> 2 -> 2, where 2 is reached twice (loop).

        // Detected the loop using slower and faster pointer
        int slower = 0, faster = 0;
        do {
            slower = nums[slower];
            faster = nums[nums[faster]];
        } while (nums[slower] != nums[faster]);

        // Slower and faster run on the same pace, then the location they meet is the duplicate one
        slower = 0;
        while (nums[slower] != nums[faster]) {
            slower = nums[slower];
            faster = nums[faster];
        }

        return nums[slower];
    }

    public static void main(String[] args) {
        FindSingleDuplicate sol = new FindSingleDuplicate();
        System.out.println(sol.findDuplicate(new int[]{2, 5, 1, 1, 4, 3}));
    }
}
