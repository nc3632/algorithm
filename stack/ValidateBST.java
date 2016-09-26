import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 *   2
 *  / \
 * 1   3
 * Binary tree [2,1,3], return true.
 *
 * Example 2:
 *   1
 *  / \
 * 2   3
 * Binary tree [1,2,3], return false.
 */

public class ValidateBST {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode = root, prevNode = null;
        while (currNode != null || !stack.isEmpty()) {
            if (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            } else {
                currNode = stack.pop();

                if (prevNode != null && prevNode.val >= currNode.val) {
                    return false;
                } else {
                    prevNode = currNode;
                }
                currNode = currNode.right;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidateBST sol = new ValidateBST();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(sol.isValidBST(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sol.isValidBST(root));
    }
}
