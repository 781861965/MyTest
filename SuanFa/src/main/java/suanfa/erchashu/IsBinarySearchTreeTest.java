package suanfa.erchashu;

public class IsBinarySearchTreeTest {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean is, int ma, int mi) {
            isBST = is;
            max = ma;
            min = mi;
        }
    }

    public static boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info infoL = process(root.left);
        Info infoR = process(root.right);
        boolean isBSTL = true;
        boolean isBSTR = true;
        int max = root.val;
        int min = root.val;
        if (infoL != null) {
            isBSTL = infoL.isBST && infoL.max < root.val;
            max = Math.max(max, infoL.max);
            min = Math.min(min, infoL.min);
        }
        if (infoR != null) {
            isBSTR = infoR.isBST && infoR.min > root.val;
            max = Math.max(max, infoR.max);
            min = Math.min(min, infoR.min);
        }
        boolean isBST = true;
        if (!isBSTL || !isBSTR) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public static Info process2(TreeNode root) {
        if (root == null) {
            return null;
        }
        boolean isBSTL = true;
        boolean isBSTR = true;
        int max = root.val;
        int min = root.val;
        Info infoL = process(root.left);
        if (infoL != null) {
            isBSTL = infoL.isBST && infoL.max < root.val;
            if (!isBSTL) {
                return new Info(isBSTL, 0, 0);
            }
            max = Math.max(max, infoL.max);
            min = Math.min(min, infoL.min);
        }
        Info infoR = process(root.right);
        if (infoR != null) {
            isBSTR = infoR.isBST && infoR.min > root.val;
            if (!isBSTR) {
                return new Info(isBSTR, 0, 0);
            }
            max = Math.max(max, infoR.max);
            min = Math.min(min, infoR.min);
        }
        boolean isBST = true;
        if (!isBSTL || !isBSTR) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }
}
