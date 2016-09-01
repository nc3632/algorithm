/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called
 * the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief
 * realized that "all houses in this place forms a binary tree". It will automatically contact the police if two
 * directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *   3
 *  / \
 * 2   3
 *  \   \
 *   3   1
 *
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Example 2:
 *     3
 *    / \
 *   4   5
 *  / \   \
 * 1   3   1
 *
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int rob(TreeNode root) {
        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }

    public int[] helper(TreeNode root) {
        int[] result = new int[2];

        if (root == null) {
            return result;
        }

        result[0] = root.val;
        if (root.left != null) {
            int[] leftChildResult = helper(root.left);
            result[0] += leftChildResult[1];
            result[1] += Math.max(leftChildResult[0], leftChildResult[1]);
        }

        if (root.right != null) {
            int[] rightChildResult = helper(root.right);
            result[0] += rightChildResult[1];
            result[1] += Math.max(rightChildResult[0], rightChildResult[1]);
        }

        return result;
    }
}
