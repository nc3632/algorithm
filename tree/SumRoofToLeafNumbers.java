/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * For example,
 *
 *   1
 *  / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 *
 * Return the sum = 12 + 13 = 25.
 */

public class SumRoofToLeafNumbers {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode root, int currentSum) {
        if (root == null) {
            return 0;
        }

        currentSum = currentSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return currentSum;
        } else {
            return sumNumbers(root.left, currentSum) + sumNumbers(root.right, currentSum);
        }
    }
}
