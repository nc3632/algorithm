import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Given nums = [5, 2, 6, 1]
 *
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0].
 */

public class CountSmallerNumbersAfterSelf {
    private static class TreeNode {
        public TreeNode left, right;
        public int val, countLeftChildren, countDuplicates;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        Integer[] result = new Integer[nums.length];
        TreeNode root = new TreeNode(nums[nums.length - 1]);

        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = insert(root, nums[i]);
        }

        return Arrays.asList(result);
    }

    public int insert(TreeNode root, int val) {
        int count = 0;
        TreeNode currNode = root;
        while (currNode.val != val) {
            if (currNode.val < val) {
                if (currNode.right == null) {
                    currNode.right = new TreeNode(val);
                }

                count += currNode.countLeftChildren + currNode.countDuplicates;
                currNode = currNode.right;
            } else {
                if (currNode.left == null) {
                    currNode.left = new TreeNode(val);
                }

                currNode.countLeftChildren++;
                currNode = currNode.left;
            }
        }

        currNode.countDuplicates++;
        return count + currNode.countLeftChildren;
    }
}
