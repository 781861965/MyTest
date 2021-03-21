package suanfa.lianbiao;

// 测试链接：https://leetcode.com/problems/merge-two-sorted-lists
public class MergeTwoSortedLinkedListTest {

    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = l1.val < l2.val ? l1 : l2;
        if (head == l1) {
            l1 = l1.next;
        } else {
            l2 = l2.next;
        }
        ListNode next = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                next.next = l1;
                l1 = l1.next;
            } else {
                next.next = l2;
                l2 = l2.next;
            }
            next = next.next;
        }
        if (l1 != null) {
            next.next = l1;
        }
        if (l2 != null) {
            next.next = l2;
        }
        return head;
    }
}
