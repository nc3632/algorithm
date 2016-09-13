/**
 * Implement Treemap.
 */

public class MyTreeMap {
    private static class TreeNode {
        public int key;
        public int val;

        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private TreeNode root;

    public void insert(int key, int val) {
        root = insert(root, key ,val);
    }

    private TreeNode insert(TreeNode node, int key, int val) {
        if (node == null) {
            return new TreeNode(key, val);
        }

        if (key == node.key) {
            node.val = val;
        } else if (key < node.key) {
            node.left = insert(node.left, key, val);
        } else {
            node.right = insert(node.right, key ,val);
        }

        return node;
    }

    public Integer getValue(int key) {
        return getValue(root, key);
    }

    private Integer getValue(TreeNode node, int key) {
        if (node == null) {
            return null;
        } else if (node.key == key) {
            return node.val;
        } else if (key < node.key) {
            return getValue(node.left, key);
        } else {
            return getValue(node.right, key);
        }
    }

    public void erase(int key) {
        TreeNode dummyNode = new TreeNode(0, 0);
        dummyNode.left = root;
        erase(dummyNode, root, key);
        root = dummyNode.left;
    }

    private void erase(TreeNode parent, TreeNode node, int key) {
        if (node == null) {
            return;
        }

        if (node.key == key) {
            TreeNode replacement;
            if (node.right == null) {
                replacement = node.left;
            } else {
                replacement = node.right;
                while (replacement.left != null) {
                    replacement = replacement.left;
                }
            }

            if (parent.left == node) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
        } else if (key < node.key) {
            erase(node, node.left, key);
        } else {
            erase(node, node.right, key);
        }
    }

    public static void main(String[] args) {
        MyTreeMap sol = new MyTreeMap();
        sol.insert(5, 5);
        sol.insert(6, 6);
        sol.insert(3, 3);
        sol.insert(4, 4);

        System.out.println("Should be 5: " + sol.getValue(5));
        System.out.println("Should be 4: " + sol.getValue(4));

        sol.insert(4, 10);
        System.out.println("Should be 10: " + sol.getValue(4));

        sol.erase(6);
        System.out.println("Should be null: " + sol.getValue(6));

        sol.erase(3);
        System.out.println("Should be 10: " + sol.getValue(4));
        System.out.println("Should be null: " + sol.getValue(3));

        sol.erase(5);
        System.out.println("Should be null: " + sol.getValue(5));
        System.out.println("Should be 4: " + sol.getValue(4));
    }
}
