/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 *
 * What if the given tree could be any binary tree? Would your previous solution still work?
 *
 * Note:
 *
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *      1
 *    /  \
 *   2    3
 *  / \    \
 * 4   5    7
 *
 * After calling your function, the tree should look like:
 *      1 -> NULL
 *    /  \
 *   2 -> 3 -> NULL
 *  / \    \
 * 4-> 5 -> 7 -> NULL
 */

public class PopulateNextPointerII {
    private static class TreeLinkNode {
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
        int val;

        TreeLinkNode (int val) {
            this.val = val;
        }
    }

    public void connect(TreeLinkNode root) {
        TreeLinkNode leftMostNode = root;
        while (leftMostNode != null) {
            TreeLinkNode currNode = leftMostNode;
            TreeLinkNode prevNode = null;

            leftMostNode = null;
            while (currNode != null) {
                if (currNode.left != null) {
                    if (leftMostNode == null) {
                        leftMostNode = currNode.left;
                    }

                    if (prevNode != null) {
                        prevNode.next = currNode.left;
                    }

                    prevNode = currNode.left;
                }

                if (currNode.right != null) {
                    if (leftMostNode == null) {
                        leftMostNode = currNode.right;
                    }

                    if (prevNode != null) {
                        prevNode.next = currNode.right;
                    }

                    prevNode = currNode.right;
                }

                currNode = currNode.next;
            }
        }
    }

    public static void main(String[] args) {
        PopulateNextPointerII sol = new PopulateNextPointerII();

        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);

        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);

        root.right.right = new TreeLinkNode(7);

        sol.connect(root);
    }
}
