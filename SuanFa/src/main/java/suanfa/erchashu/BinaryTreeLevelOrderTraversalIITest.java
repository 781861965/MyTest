package suanfa.erchashu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 测试链接：https://leetcode.com/problems/binary-tree-level-order-traversal-ii
public class BinaryTreeLevelOrderTraversalIITest {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<List<Integer>>();
        if (root == null) {
            return lists;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<Integer>(size);
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            lists.add(0,list);
        }
        return lists;
    }
}
