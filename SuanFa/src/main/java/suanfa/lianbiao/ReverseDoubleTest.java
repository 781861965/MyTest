package suanfa.lianbiao;

public class ReverseDoubleTest {

    public static void main(String[] args) {
        DoubleNode doubleNode1 = new DoubleNode(1, null);
        DoubleNode doubleNode2 = new DoubleNode(2, doubleNode1);
        doubleNode1.next = doubleNode2;
        DoubleNode doubleNode3 = new DoubleNode(3, doubleNode2);
        doubleNode2.next = doubleNode3;
        DoubleNode doubleNode = reverseDoubleList(doubleNode1);
        System.out.println(doubleNode.value);

    }


    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode doubleNode = null;
        while (head != null) {
            DoubleNode next = head.next;
            head.next = head.last;
            head.last = next;
            doubleNode = head;
            head = next;
        }
        return doubleNode;
    }


    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int value, DoubleNode last) {
            this.value = value;
            this.last = last;
        }
    }
}
