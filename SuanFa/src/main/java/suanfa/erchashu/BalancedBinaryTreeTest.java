package suanfa.erchashu;

// 测试链接：https://leetcode.com/problems/balanced-binary-tree
public class BalancedBinaryTreeTest {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info infoL = process(root.left);
        Info infoR = process(root.right);
        return new Info(infoL.isBalanced && infoR.isBalanced && Math.abs(infoL.height - infoR.height) <= 1, Math.max(infoL.height, infoR.height) + 1);
    }

    private static Info process2(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info infoL = process(root.left);
        if (!infoL.isBalanced) {
            return infoL;
        }
        Info infoR = process(root.right);
        if (!infoR.isBalanced) {
            return infoR;
        }
        return new Info(Math.abs(infoL.height - infoR.height) <= 1, Math.max(infoL.height, infoR.height) + 1);
    }
}
