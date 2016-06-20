import java.util.Stack;

/**
 * Implement the following operations of a queue using stacks.
 *
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size,
 * and isEmpty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a
 * list or deque(double-ended queue), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on
 * an empty queue).
 */
public class QueueUsingStack {
    private Stack[] stacks = new Stack[] {new Stack<Integer>(), new Stack<Integer>()};
    private int active = 0;

    // Push element x to the back of queue.
    public void push(int x) {
        if (active == 1) {
            move(1, 0);
        }
        stacks[0].push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (active == 0) {
            move(0, 1);
        }
        stacks[1].pop();
    }

    // Get the front element.
    public int peek() {
        if (active == 0) {
            move(0, 1);
        }
        return (int) stacks[1].peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stacks[active].isEmpty();
    }

    private void move(int from, int to) {
        while (!stacks[from].isEmpty()) {
            stacks[to].push(stacks[from].pop());
        }
        active = to;
    }
}
