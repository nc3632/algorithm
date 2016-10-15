/**
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * For example:
 * Given binary tree,
 *     5
 *    / \
 *   1   5
 *  / \   \
 * 5   5   5
 * return 4.
 */

public class CountUniqueValueSubTrees {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int uniqueFromLeft = countUnivalSubtrees(root.left);
        int uniqueFromRight = countUnivalSubtrees(root.right);

        int count = uniqueFromLeft + uniqueFromRight;
        if ((uniqueFromLeft == 0 || uniqueFromLeft == 1 && root.val == root.left.val)
                && (uniqueFromRight == 0 || uniqueFromRight == 1 && root.val == root.right.val)) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        CountUniqueValueSubTrees sol = new CountUniqueValueSubTrees();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);

        System.out.println(sol.countUnivalSubtrees(root));
    }
}
