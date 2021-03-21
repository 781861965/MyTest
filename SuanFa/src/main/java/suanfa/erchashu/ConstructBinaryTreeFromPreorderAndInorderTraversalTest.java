package suanfa.erchashu;

import java.util.HashMap;
import java.util.Map;

//测试链接：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
public class ConstructBinaryTreeFromPreorderAndInorderTraversalTest {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0 || pre.length != in.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return f(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    // 有一棵树，先序结果是pre[L1...R1]，中序结果是in[L2...R2]
    // 请建出整棵树返回头节点
    public static TreeNode f(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1) {
            return null;
        }
        if (l1 == r1) {
            return new TreeNode(pre[l1]);
        }
        TreeNode treeNode = new TreeNode(pre[l1]);
        int index = map.get(pre[l1]);
        treeNode.left = f(pre, l1 + 1, l1 + index - l2, in, l2, index - 1, map);
        treeNode.right = f(pre, l1 + index - l2 + 1, r1, in, index + 1, index - 1, map);
        return treeNode;
    }
}
