import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *      1
 *     / \
 *    2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */

public class SymmetricTree {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return isSymmetric(root.left, root.right);
        }
    }

    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null && root2 != null
                || root1 != null && root2 == null
                || root1.val != root2.val) {

            return false;
        } else {
            return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
        }
    }

    private boolean isSymmetricIterative(TreeNode root) {
        if (root == null) {
            return true;
        }

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0, j = size - 1; i <= j; i++, j--) {
                TreeNode node1 = queue.get(i);
                TreeNode node2 = queue.get(j);
                if (node1 == null && node2 != null
                        || node1 != null && node2 == null
                        || node1 != null && node2 != null
                        && (node1.val != node2.val || node1.val != node2.val)) {
                    return false;
                }
            }

            List<TreeNode> tempQueue = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.get(i);
                if (node != null) {
                    tempQueue.add(node.left);
                    tempQueue.add(node.right);
                }
            }
            queue = tempQueue;
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        SymmetricTree sol = new SymmetricTree();
        System.out.println(sol.isSymmetricIterative(root));
    }
}
