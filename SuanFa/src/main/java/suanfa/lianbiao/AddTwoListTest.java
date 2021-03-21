package suanfa.lianbiao;

import java.math.BigInteger;

// 测试链接：https://leetcode.com/problems/add-two-numbers/
public class AddTwoListTest {
    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(3, null);
        ListNode listNode4 = new ListNode(4, listNode3);
        ListNode listNode2 = new ListNode(2, listNode4);
        out(listNode2);

        ListNode listNode24 = new ListNode(4, null);
        ListNode listNode6 = new ListNode(6, listNode24);
        ListNode listNode5 = new ListNode(5, listNode6);
        out(listNode5);

        //     out( addTwoNumbers( listNode2, listNode5));
        out(addTwoNumbers6(listNode2, listNode5));
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

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }

        ListNode l = listSize(head1) > listSize(head2) ? head1 : head2;
        ListNode s = l == head2 ? head1 : head2;
        ListNode head = l;
        ListNode last = l;
        int carry = 0;
        int curNum = 0;
        while (s != null) {
            curNum = l.val + s.val + carry;
            l.val = curNum % 10;
            carry = curNum / 10;
            last = l;
            l = l.next;
            s = s.next;
        }
        while (l != null) {
            curNum = l.val + carry;
            l.val = carry % 10;
            carry = curNum / 10;
            last = l;
            l = l.next;
        }
        if (carry != 0) {
            last.next = new ListNode(carry);
        }
        return head;
    }

    // 求链表长度
    public static int listSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public static ListNode addTwoNumbers6(ListNode head1, ListNode head2) {
        return bigIntegerToNode(nodeToBigInteger(head1).add(nodeToBigInteger(head2)));
    }

    public static BigInteger nodeToBigInteger(ListNode node) {
        StringBuffer sb = new StringBuffer();
        while (node != null) {
            sb.append(node.val);
            node = node.next;
        }
        return new BigInteger(sb.reverse().toString());
    }

    public static ListNode bigIntegerToNode(BigInteger bigInteger) {
        ListNode listNode = new ListNode(0);
        ListNode next = listNode;
        String s = bigInteger.toString();
        for (int i = s.length() - 1; i >= 0; i--) {
            next.next = new ListNode(Integer.valueOf(s.charAt(i) + ""));
            next = next.next;
        }
        return listNode.next;
    }
}
