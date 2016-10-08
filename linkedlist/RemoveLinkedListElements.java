/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 */

public class RemoveLinkedListElements {
    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            this.val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(1);
        dummyHead.next = head;

        ListNode currNode = dummyHead;
        while (currNode.next != null) {
            if (currNode.next.val == val) {
                currNode.next = currNode.next.next;
            } else {
                currNode = currNode.next;
            }
        }

        return dummyHead.next;
    }
}
