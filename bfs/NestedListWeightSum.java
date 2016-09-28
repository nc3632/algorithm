import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 *
 * Example 2:
 * Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
 */
public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        int depth = 0;
        return depthSum(nestedList, depth);
    }

    private int depthSum(List<NestedInteger> nestedList, int depth) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * depth;
            } else {
                sum += depthSum(nestedInteger.getList(), depth + 1);
            }
        }

        return sum;
    }

    public int depthSumBFS(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }

        Queue<NestedInteger> queue = new LinkedList<>();

        for (NestedInteger nestedInteger : nestedList) {
            queue.offer(nestedInteger);
        }

        int sum = 0;
        int depth = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger()) {
                    sum += ni.getInteger() * depth;
                } else {
                    queue.addAll(ni.getList());
                }
            }

            depth++;
        }

        return sum;
    }
}
