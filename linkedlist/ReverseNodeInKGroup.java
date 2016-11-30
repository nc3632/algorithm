/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * You may not alter the values in the nodes, only nodes itself may be changed.
 *
 * Only constant memory is allowed.
 *
 * For example,
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 */

public class ReverseNodeInKGroup {
    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            this.val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyNode = new ListNode(0), tail = dummyNode;
        dummyNode.next = head;

        ListNode groupHead = head, groupTail = head;
        int index = 0;
        while (groupTail != null) {
            groupTail = groupTail.next;
            index++;

            if (index % k == 0) {
                tail.next = reverse(groupHead, groupTail);
                tail = groupHead;
                groupHead = groupTail;
            }
        }

        return dummyNode.next;
    }

    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode prevNode = tail, currNode = head;
        while (currNode != tail) {
            ListNode temp = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = temp;
        }

        return prevNode;
    }
}
