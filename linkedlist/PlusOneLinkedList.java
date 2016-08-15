/**
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example:
 * Input:
 * 1->2->3
 *
 * Output:
 * 1->2->4
 */

public class PlusOneLinkedList {
    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            this.val = x;
        }
    }

    /**
     * Reverse so that carry-on can be computed.
     */
    public ListNode plusOne(ListNode head) {
        head = reverse(head);
        ListNode currNode = head, prevNode = head;
        int carryOn = 0;
        do {
            if (currNode.val == 9) {
                carryOn = 1;
                currNode.val = 0;
            } else {
                carryOn = 0;
                currNode.val += 1;
            }

            prevNode = currNode;
            currNode = currNode.next;
        } while (currNode != null && carryOn == 1);

        if (carryOn == 1) {
            prevNode.next = new ListNode(1);
        }

        return reverse(head);
    }

    public ListNode reverse(ListNode head) {
        ListNode newHead = null, currNode = head;
        while (currNode != null) {
            ListNode temp = currNode.next;
            currNode.next = newHead;
            newHead = currNode;
            currNode = temp;
        }

        return newHead;
    }

    /**
     * Recursion so that no carry-on is needed.
     */
    public ListNode plusOneRecursive(ListNode head) {
        int carry = helper(head);
        if (carry == 0) {
            return head;
        } else {
            ListNode newHead = new ListNode(carry);
            newHead.next = head;
            return newHead;
        }
    }

    private int helper(ListNode head) {
        int newVal;
        if (head.next == null) {
            newVal = head.val + 1;
        } else {
            newVal = head.val + helper(head.next);
        }

        head.val = newVal % 10;
        return newVal / 10;
    }

    /**
     *  The fastest way is to find the last non-9 node.
     *  1 is to be added to this node. If no such a node can be found,
     *  we will need to add a new node.
     *  All 9 nodes need to be changed to 0.
     */
    public ListNode plusOneFastest(ListNode head) {
        ListNode targetNode = null, currNode = head;
        while (currNode != null) {
            if (currNode.val != 9) {
                targetNode = currNode;
            }
            currNode = currNode.next;
        }

        if (targetNode == null) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            head = newHead;
            targetNode = head;
        } else {
            targetNode.val++;
        }

        currNode = targetNode.next;
        while (currNode != null) {
            currNode.val = 0;
            currNode = currNode.next;
        }

        return head;
    }

    private ListNode num2List(int num) {
        ListNode head = null;
        while (num != 0) {
            ListNode node = new ListNode(num % 10);
            node.next = head;
            head = node;

            num /= 10;
        }

        return head;
    }

    private int list2Num(ListNode head) {
        int num = 0;
        while (head != null) {
            num = num * 10 + head.val;
            head = head.next;
        }

        return num;
    }

    public static void main(String[] args) {
        PlusOneLinkedList sol = new PlusOneLinkedList();
        System.out.println(sol.list2Num(sol.plusOne(sol.num2List(123))));
        System.out.println(sol.list2Num(sol.plusOneRecursive(sol.num2List(123))));
        System.out.println(sol.list2Num(sol.plusOneFastest(sol.num2List(123))));
        System.out.println(sol.list2Num(sol.plusOne(sol.num2List(129))));
        System.out.println(sol.list2Num(sol.plusOneRecursive(sol.num2List(129))));
        System.out.println(sol.list2Num(sol.plusOneFastest(sol.num2List(129))));
        System.out.println(sol.list2Num(sol.plusOne(sol.num2List(193))));
        System.out.println(sol.list2Num(sol.plusOneRecursive(sol.num2List(193))));
        System.out.println(sol.list2Num(sol.plusOneFastest(sol.num2List(193))));
        System.out.println(sol.list2Num(sol.plusOne(sol.num2List(993))));
        System.out.println(sol.list2Num(sol.plusOneRecursive(sol.num2List(993))));
        System.out.println(sol.list2Num(sol.plusOneFastest(sol.num2List(993))));
        System.out.println(sol.list2Num(sol.plusOne(sol.num2List(999))));
        System.out.println(sol.list2Num(sol.plusOneRecursive(sol.num2List(999))));
        System.out.println(sol.list2Num(sol.plusOneFastest(sol.num2List(999))));
    }
}
