/**
 * Given a Binary Tree, write a function that returns the size of the largest subtree which is also a Binary Search
 * Tree (BST). If the complete Binary Tree is BST, then return the size of whole tree.
 *
 * Examples:
 *
 * Input:
 *       5
 *     /  \
 *    2    4
 *  /  \
 * 1    3
 *
 * Output: 3
 * The following subtree is the maximum size BST subtree
 *    2
 *  /  \
 * 1    3
 *
 * Input:
 *       50
 *     /    \
 *   30       60
 *  /  \     /  \
 * 5   20   45    70
 *               /  \
 *             65    80
 * Output: 5
 * The following subtree is the maximum size BST subtree
 *   60
 *  /  \
 * 45    70
 *      /  \
 *     65    80
 */

public class LargestBstSubTree {
    private static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static class Result {
        boolean isBST;
        int largestSize;
        int lowerBound;
        int upperBound;

        Result(boolean isBST, int largestSize, int lowerBound, int upperBound) {
            this.isBST = isBST;
            this.largestSize = largestSize;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    public Result largestBST(TreeNode node) {
        if (node == null) {
            return new Result(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Result resultFromLeft = largestBST(node.left);
        Result resultFromRight = largestBST(node.right);

        if (resultFromLeft.isBST && resultFromRight.isBST
                && resultFromLeft.upperBound < node.val && node.val < resultFromRight.lowerBound) {

            return new Result(true, resultFromLeft.largestSize + resultFromRight.largestSize + 1,
                    resultFromLeft.largestSize == 0 ? node.val : resultFromLeft.lowerBound,
                    resultFromRight.largestSize == 0 ? node.val : resultFromRight.upperBound);
        } else {
            return new Result(false, Math.max(resultFromLeft.largestSize, resultFromRight.largestSize),
                    Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
    }

    public static void main(String[] args) {
        LargestBstSubTree sol = new LargestBstSubTree();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println(sol.largestBST(root).largestSize);

        root = new TreeNode(50);
        root.left = new TreeNode(30);
        root.right = new TreeNode(60);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(20);
        root.right.left = new TreeNode(45);
        root.right.right = new TreeNode(70);
        root.right.right.left = new TreeNode(65);
        root.right.right.right = new TreeNode(80);

        System.out.println(sol.largestBST(root).largestSize);
    }
}
