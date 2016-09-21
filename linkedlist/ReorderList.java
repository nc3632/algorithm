/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You must do this in-place without altering the nodes' values.
 *
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class ReorderList {
    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            this.val = x;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slower = head, faster = head;
        while (faster.next != null && faster.next.next != null) {
            slower = slower.next;
            faster = faster.next.next;
        }

        ListNode secondHalf = reverse(slower.next);
        slower.next = null;

        ListNode currNode = head;
        while (secondHalf != null) {
            ListNode temp = currNode.next;
            currNode.next = secondHalf;
            secondHalf = secondHalf.next;
            currNode.next.next = temp;
            currNode = temp;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode currNode = head.next;
        head.next = null;

        while (currNode != null) {
            ListNode temp = currNode.next;
            currNode.next = head;
            head = currNode;
            currNode = temp;
        }

        return head;
    }

    public static void main(String[] args) {
        ReorderList sol = new ReorderList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        sol.reorderList(head);
    }
}
