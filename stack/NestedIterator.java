import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 *
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 * [1,1,2,1,1].
 *
 * Example 2:
 * Given the list [1,[4,[6]]],
 *
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 * [1,4,6].
 */

interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {
    private Stack<ListIterator<NestedInteger>> stack;
    private boolean checked;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.listIterator());
    }

    @Override
    public Integer next() {
        if (checked) {
            checked = false;
            return stack.peek().next().getInteger();
        } else {
            return hasNext() ? stack.peek().next().getInteger() : null;
        }
    }

    @Override
    public boolean hasNext() {
        checked = true;

        while (!stack.isEmpty()) {
            ListIterator<NestedInteger> iterator = stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
            } else {
                NestedInteger nextInt = iterator.next();
                if (nextInt.isInteger()) {
                    iterator.previous();
                    return true;
                } else {
                    stack.push(nextInt.getList().listIterator());
                }
            }
        }
        return false;
    }
}
