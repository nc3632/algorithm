/**
 * Given a binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * For example:
 * Given the below binary tree,
 *
 *       1
 *      / \
 *     2   3
 * Return 6.
 */

public class BinaryTreeMaxPathSum {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    class Result {
        int maxRootToLeaf;
        int max;
    }

    public int maxPathSum(TreeNode root) {
        Result result = maxPathSumHelper(root);
        return result.max;
    }

    public Result maxPathSumHelper(TreeNode root) {
        Result result = new Result();

        if (root == null) {
            result.max = Integer.MIN_VALUE;
            return result;
        }

        Result resultLeft = maxPathSumHelper(root.left);
        Result resultRight = maxPathSumHelper(root.right);

        result.maxRootToLeaf = Math.max(0, root.val + Math.max(resultLeft.maxRootToLeaf, resultRight.maxRootToLeaf));
        result.max = Math.max(Math.max(resultLeft.max, resultRight.max), root.val + resultLeft.maxRootToLeaf +
                resultRight.maxRootToLeaf);
        return result;
    }
}
