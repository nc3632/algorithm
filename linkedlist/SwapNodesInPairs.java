/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be
 * changed.
 */

public class SwapNodesInPairs {
    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode prevNode = dummyNode, currNode = dummyNode.next;
        while (currNode != null && currNode.next != null) {
            prevNode.next = swap(currNode, currNode.next);
            prevNode = prevNode.next.next;
            currNode = prevNode.next;
        }

        return dummyNode.next;
    }

    private ListNode swap(ListNode first, ListNode second) {
        first.next = second.next;
        second.next = first;
        return second;
    }
}
