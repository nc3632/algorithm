/**
 * Given a linked list, determine if it has a cycle in it.
 */

public class LinkedListCycle {
    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            this.val = x;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode faster = head;
        ListNode slower = head;
        while (faster != null && faster.next != null) {
            faster = faster.next.next;
            slower = slower.next;

            if (faster == slower) {
                return true;
            }
        }

        return false;
    }
}
