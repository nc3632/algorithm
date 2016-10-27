import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a binary tree, print it vertically. The following example illustrates vertical order traversal.
 *
 *       1
 *    /    \
 *   2      3
 *  / \    / \
 * 4   5  6   7
 *        \   \
 *         8   9
 *
 *
 * The output of print this tree vertically will be:
 * 4
 * 2
 * 1 5 6
 * 3 8
 * 7
 * 9
 */

public class VerticalTraversal {
    private static class Pair {
        int col;
        TreeNode node;

        public Pair(int col, TreeNode node) {
            this.col = col;
            this.node = node;
        }
    }

    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, root));

        Map<Integer, List<Integer>> map = new HashMap<>();
        int min = 0, max = 0;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            List<Integer> colList;
            if (!map.containsKey(pair.col)) {
                colList = new ArrayList<>();
                map.put(pair.col, colList);
            } else {
                colList = map.get(pair.col);
            }
            colList.add(pair.node.val);

            min = Math.min(min, pair.col);
            max = Math.max(max, pair.col);

            if (pair.node.left != null) {
                queue.add(new Pair(pair.col - 1, pair.node.left));
            }

            if (pair.node.right != null) {
                queue.add(new Pair(pair.col + 1, pair.node.right));
            }
        }

        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }

        return result;
    }

    public static void main(String[] args) {
        VerticalTraversal sol = new VerticalTraversal();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.right = new TreeNode(9);

        List<List<Integer>> result = sol.verticalOrder(root);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
}
