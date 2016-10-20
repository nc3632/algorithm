import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized
 * to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following tree
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need
 * to follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 */
public class BinaryTreeSerialization {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append(",");
            } else {
                sb.append(node.val + ",");

                queue.add(node.left);
                queue.add(node.right);
            }
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public TreeNode deserialize(String s) {
        if (s.isEmpty()) {
            return null;
        }

        String[] values = s.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 1; i < values.length; i += 2) {
            TreeNode node = queue.poll();

            if (i < values.length && !values[i].isEmpty()) {
                node.left = new TreeNode(Integer.valueOf(values[i]));
                queue.offer(node.left);
            }

            if (i + 1 < values.length && !values[i + 1].isEmpty()) {
                node.right = new TreeNode(Integer.valueOf(values[i + 1]));
                queue.offer(node.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        BinaryTreeSerialization sol = new BinaryTreeSerialization();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(5);

        String serialized = sol.serialize(root);
        TreeNode deserialized = sol.deserialize(serialized);

        System.out.println("done");
    }
}
