import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * For example, given the following binary tree:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *  5
 * All root-to-leaf paths are:
 *
 * ["1->2->5", "1->3"]
 */

public class BinaryTreePaths {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) {
            helper(root, "", paths);
        }
        return paths;
    }

    private void helper(TreeNode root, String path, List<String> paths) {
        path += root.val;

        if (root.left == null && root.right == null) {
            paths.add(path);
        } else {
            if (root.left != null) {
                helper(root.left, path + "->", paths);
            }
            if (root.right != null) {
                helper(root.right, path + "->", paths);
            }
        }
    }
}
