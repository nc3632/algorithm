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

    private class Result {
        boolean isValid;
        long max;
        long min;

        Result(boolean isValid, long max, long min) {
            this.isValid = isValid;
            this.max = max;
            this.min = min;
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

    public boolean isValidBSTRecursive(TreeNode root) {
        return helper(root).isValid;
    }

    private Result helper(TreeNode root) {
        if (root == null) {
            return new Result(true, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        Result result1 = helper(root.left);
        Result result2 = helper(root.right);

        if (result1.isValid && result2.isValid && result1.max < root.val && result2.min > root.val) {
            return new Result(true, result2.max == Long.MIN_VALUE ? root.val : result2.max,
                    result1.min == Long.MAX_VALUE ? root.val : result1.min);
        } else {
            return new Result(false, Long.MIN_VALUE, Long.MIN_VALUE);
        }
    }

    public static void main(String[] args) {
        ValidateBST sol = new ValidateBST();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(sol.isValidBSTRecursive(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sol.isValidBSTRecursive(root));
    }
}
