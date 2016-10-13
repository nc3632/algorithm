/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in
 * the list or null.
 *
 * Return a deep copy of the list.
 */

public class DeepCopyWithRandomPointer {
    private class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        // Next of original node points to the cloned node
        RandomListNode currNode = head;
        while(currNode != null) {
            currNode.next = cloneNode(currNode);
            currNode = currNode.next.next;
        }

        RandomListNode clonedHead = head.next;

        // Make random of cloned node points to the right node
        currNode = head;
        while(currNode != null) {
            RandomListNode clonedNode = currNode.next;
            if (clonedNode.random != null) {
                clonedNode.random = clonedNode.random.next;
            }

            currNode = clonedNode.next;
        }

        // Make next of cloned node points to the right node
        // Also restore the next of the original node
        currNode = head;
        while (currNode != null) {
            RandomListNode clonedNode = currNode.next;

            currNode.next = clonedNode.next;
            currNode = currNode.next;

            if (clonedNode.next != null) {
                clonedNode.next = clonedNode.next.next;
            }
        }

        return clonedHead;
    }

    private RandomListNode cloneNode(RandomListNode node) {
        RandomListNode clonedNode = new RandomListNode(node.label);
        clonedNode.next = node.next;
        clonedNode.random = node.random;

        return clonedNode;
    }
}
