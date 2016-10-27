/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * For example, given the following binary tree:
 *
 *       1
 *     /   \
 *    2     3
 *  /  \     \
 * 4    5     6
 * All root-to-leaf paths are:
 *
 * ["__1,_2,4", "__1,_2,__5", "__1,__3,___6]
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePathsWithColumns {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static class Pair {
        int min;
        int max;
    }

    private void findRange(TreeNode root, int pos, Pair range) {
        if (root == null) {
            return;
        }

        range.min = Math.min(range.min, pos);
        range.max = Math.max(range.max, pos);

        findRange(root.left, pos - 1, range);
        findRange(root.right, pos + 1, range);
    }

    private void findPaths(TreeNode root, int col, String candidate, List<String> result) {
        StringBuilder val = new StringBuilder();
        for (int i = 0; i < col; i++) {
            val.append('_');
        }
        val.append(root.val);

        if (candidate.isEmpty()) {
            candidate = val.toString();
        } else {
            candidate += "," + val.toString();
        }

        if (root.left == null && root.right == null) {
            result.add(candidate);
        } else {
            if (root.left != null) {
                findPaths(root.left, col - 1, candidate, result);
            }
            if (root.right != null) {
                findPaths(root.right, col + 1, candidate, result);
            }
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Pair range = new Pair();
        findRange(root, 0, range);
        findPaths(root, -range.min, "", result);

        return result;
    }

    public static void main(String[] args) {
        BinaryTreePathsWithColumns sol = new BinaryTreePathsWithColumns();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println(sol.binaryTreePaths(root));
    }
}
