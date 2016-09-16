import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and set.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 */

public class LeastRecentlyUsedCache {
    private static class DoublyLinkedNode {
        int key;
        int val;
        DoublyLinkedNode prev, next;

        DoublyLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;

    private DoublyLinkedNode cacheHead, cacheTail;
    private Map<Integer, DoublyLinkedNode> map = new HashMap();

    public LeastRecentlyUsedCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            DoublyLinkedNode target = map.get(key);
            moveNodeToTail(target);
            return target.val;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            DoublyLinkedNode target = map.get(key);
            target.val = value;
            moveNodeToTail(target);
        } else {
            DoublyLinkedNode node = new DoublyLinkedNode(key, value);

            map.put(key, node);

            if (cacheHead == null) {
                cacheHead = cacheTail = node;
            } else {
                cacheTail.next = node;
                node.prev = cacheTail;
                cacheTail = node;
            }

            if (map.size() > capacity) {
                map.remove(cacheHead.key);
                cacheHead = cacheHead.next;
                cacheHead.prev = null;
            }
        }
    }

    private void moveNodeToTail(DoublyLinkedNode target) {
        if (map.size() > 1 && target != cacheTail) {
            if (target != cacheHead) {
                target.prev.next = target.next;
                target.next.prev = target.prev;
            } else {
                cacheHead = target.next;
                cacheHead.prev = null;
            }

            cacheTail.next = target;
            target.prev = cacheTail;
            cacheTail = target;
        }
    }
}
