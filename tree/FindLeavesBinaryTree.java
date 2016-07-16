import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a binary tree, find all leaves and then remove those leaves.
 * Then repeat the previous steps until the tree is empty.
 * Example: Given binary tree 1
 *                           / \
 *                          2  3
 *                         / \
 *                        4  5
 * Returns [4, 5, 3], [2], [1].
 * Explanation:
 * Remove the leaves [4, 5, 3] from the tree
 *   1
 *  /
 * 2
 * Remove the leaf [2] from the tree
 * 1
 * Remove the leaf [1] from the tree
 * []
 * Returns [4, 5, 3], [2], [1].
 */

public class FindLeavesBinaryTree {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(root, result);

        return result;
    }

    private int helper(TreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return -1;
        }

        int depth = 1 + Math.max(helper(root.left, result), helper(root.right, result));
        if (depth + 1 > result.size()) {
            result.add(new ArrayList<>());
        }

        result.get(depth).add(root.val);

        return depth;
    }
}
