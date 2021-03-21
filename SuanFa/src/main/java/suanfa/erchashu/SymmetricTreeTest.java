package suanfa.erchashu;

// 测试链接：https://leetcode.com/problems/symmetric-tree
public class SymmetricTreeTest {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private static boolean isMirror(TreeNode root, TreeNode root1) {
        if (root == null && root1 == null) {
            return true;
        }
        if (root == null || root1 == null) {
            return false;
        }
        return root.val == root1.val && isMirror(root.left, root1.right) && isMirror(root.right, root1.left);
    }
}
