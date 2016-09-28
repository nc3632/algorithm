/**
 * Tournament tree, the winner is at the root. Find the second best player.
 */

public class SecondBestPlayer {
    private static class TreeNode {
        int val;
        TreeNode left, right;
    }

    public int findSecondBest(TreeNode root) {
        // The second best was only won by the best player
        int result = Integer.MIN_VALUE;
        TreeNode currNode = root;
        while (currNode != null && currNode.left != null && currNode.right != null) {
            if (currNode.val == currNode.left.val) {
                result = Math.max(result, currNode.right.val);
                currNode = currNode.left;
            } else {
                result = Math.max(result, currNode.left.val);
                currNode = currNode.right;
            }
        }

        return result;
    }
}
