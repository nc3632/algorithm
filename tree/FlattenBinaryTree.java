/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example,
 * Given
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 *  \
 *   2
 *   \
 *   3
 *    \
 *    4
 *     \
 *     5
 *      \
 *      6
 */

public class FlattenBinaryTree {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public void flatten(TreeNode root) {
        TreeNode currNode = root;
        while (currNode != null) {
            if (currNode.left != null) {
                TreeNode rightmost = currNode.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }

                rightmost.right = currNode.right;
                currNode.right = currNode.left;
                currNode.left = null;
            }

            currNode = currNode.right;
        }
    }

    // Recursion solution
    public void flattenRecursive(TreeNode root) {
        helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftTail = helper(root.left);
        TreeNode rightTail = helper(root.right);

        if (leftTail != null) {
            leftTail.right = root.right;

            root.right = root.left;
            root.left = null;
        }

        if (leftTail == null && rightTail == null) {
            return root;
        } else if (rightTail != null) {
            return rightTail;
        } else {
            return leftTail;
        }
    }
}
