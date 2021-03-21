package suanfa.lianbiao;

// 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
public class ReverseNodesInKGroupTest {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        out(node1);
        node1 = reverseKGroup(node1, 2);
        out(node1);
    }

    public static void out(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println("=====================");
    }


    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        reverse(start, end);
        head = end;
        ListNode next = start.next;
        end = getKGroupEnd(next, k);
        while (end != null) {
            reverse(next, end);
            start.next = end;
            start = next;
            next = next.next;
            end = getKGroupEnd(next, k);
        }
        return head;
    }

    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k > 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode node0 = start;
        ListNode node1 = start.next;
        while (node1 != end) {
            ListNode node2 = node1.next;
            node1.next = node0;
            node0 = node1;
            node1 = node2;
        }
        start.next = end;
    }
}
