/**
 * Given a root of Binary Search Tree with unique value for each node. Remove the node with given value.
 * If there is no such a node with given value in the binary search tree, do nothing.
 * You should keep the tree still a binary search tree after removal.
 */

public class RemoveNodeBST {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode removeNode(TreeNode root, int value) {
        TreeNode dummyRoot = new TreeNode(0);
        dummyRoot.left = root;

        // Find the target node and its parent
        TreeNode parentNode = dummyRoot, targetNode = root;
        while (targetNode != null && targetNode.val != value) {
            parentNode = targetNode;
            targetNode = targetNode.val > value ? targetNode.left : targetNode.right;
        }

        if (targetNode == null) {
            return root;
        }

        // Delete the target node
        TreeNode replacementNode;
        if (targetNode.right == null) {
            // If the right child of the target node is empty
            // simply replace the target node with the target node's left child.
            replacementNode = targetNode.left;
        } else {
            // If the right child of the target node is not empty
            // need to attach the target's left node to the leftmost child of the right child.
            TreeNode leftmostChild = targetNode.right;
            while (leftmostChild.left != null) {
                leftmostChild = leftmostChild.left;
            }

            leftmostChild.left = targetNode.left;
            replacementNode = targetNode.right;
        }

        if (parentNode.left == targetNode) {
            parentNode.left = replacementNode;
        } else {
            parentNode.right = replacementNode;
        }

        return dummyRoot.left;
    }
}
