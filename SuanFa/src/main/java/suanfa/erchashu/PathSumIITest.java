package suanfa.erchashu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSumIITest {

    // 测试链接：https://leetcode.com/problems/path-sum-ii
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<Integer> path = new LinkedList<Integer>();
        process(root, path, 0, sum, ans);
        return ans;
    }

    private static void process(TreeNode root, LinkedList<Integer> path, int preSum, int sum, List<List<Integer>> ans) {
        if (root.left == null && root.right == null) {
            if (root.val + preSum == sum) {
                path.add(root.val);
                ans.add(new LinkedList<>(path));
                path.pollLast();
            }
            return;
        }
        path.add(root.val);
        preSum += root.val;
        if (root.left != null) {
            process(root.left, path, preSum, sum, ans);
        }
        if (root.right != null) {
            process(root.right, path, preSum, sum, ans);
        }
        path.pollLast();
    }


}
