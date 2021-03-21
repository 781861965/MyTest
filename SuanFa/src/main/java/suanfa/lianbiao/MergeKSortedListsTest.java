package suanfa.lianbiao;

import java.util.Comparator;
import java.util.PriorityQueue;

// 测试链接：https://leetcode.com/problems/merge-k-sorted-lists/
public class MergeKSortedListsTest {

    public static class ListNode {
        public int val;
        public ListNode next;
    }


    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return getListNode(lists);
    }

    private static ListNode getListNode(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((o1, o2) -> o1.val - o2.val);
        for (ListNode listNode : lists) {
            if(listNode !=  null){
                queue.add(listNode);
            }
        }
        if (queue.isEmpty()) {
            return null;
        }
        ListNode node = queue.poll();
        ListNode pre = node;
        if (pre.next != null) {
            queue.add(pre.next);
        }
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            pre.next = cur;
            pre = cur;
            if (pre.next != null) {
                queue.add(pre.next);
            }
        }
        return node;
    }
}
