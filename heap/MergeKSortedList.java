import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */

public class MergeKSortedList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                ListNode n1 = (ListNode) o1;
                ListNode n2 = (ListNode) o2;
                return n1.val - n2.val;
            }
        });

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.offer(lists[i]);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (!pq.isEmpty()) {
            ListNode winner = pq.poll();
            tail.next = winner;
            tail = tail.next;

            if (winner.next != null) {
                pq.offer(winner.next);
            }
        }

        return dummyHead.next;
    }
}
