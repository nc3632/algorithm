import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from
 * bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 *
 * Example 2:
 * Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 =
 *  17)
 */

public class NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger ni : nestedList) {
            queue.offer(ni);
        }

        int unweighted = 0, weighted = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger()) {
                    unweighted += ni.getInteger();
                } else {
                    queue.addAll(ni.getList());
                }
            }
            weighted += unweighted;
        }

        return weighted;
    }
}
