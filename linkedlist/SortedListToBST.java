/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class SortedListToBST {
    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            this.val = x;
        }
    }

    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    /**
     * O(nlogn)
     */
    private TreeNode sortedListToBST(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }

        ListNode faster = head, slower = head;
        while (faster != tail && faster.next != tail) {
            faster = faster.next.next;
            slower = slower.next;
        }

        TreeNode node = new TreeNode(slower.val);
        node.left = sortedListToBST(head, slower);
        node.right = sortedListToBST(slower.next, tail);

        return node;
    }

    private ListNode currNode;    // Used on O(logn) solution

    public TreeNode sortedListToBSTLinear(ListNode head) {
        currNode = head;
        int size = getLength(head);

        return buildSubTree(size);
    }

    private int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    /**
     * O(logn)
     */
    private TreeNode buildSubTree(int size) {
        if (size == 0) {
            return null;
        }

        // Build left subtree
        TreeNode leftTree = buildSubTree(size / 2);

        // Root node
        TreeNode root = new TreeNode(currNode.val);

        // Need to move the current ListNode to the next one,
        // which then will be used by its right subtree
        currNode = currNode.next;

        // Build right subtree
        TreeNode rightTree = buildSubTree(size - size / 2 - 1);

        root.left = leftTree;
        root.right = rightTree;

        return root;
    }
}
