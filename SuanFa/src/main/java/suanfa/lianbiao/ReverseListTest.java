package suanfa.lianbiao;

public class ReverseListTest {

    public static void main(String[] args) {
        Node doubleNode1 = new Node(1, null);
        Node doubleNode2 = new Node(2, doubleNode1);
        Node doubleNode3 = new Node(3, doubleNode2);
        System.out.println(doubleNode3.value);
        Node doubleNode = reverseDoubleList(doubleNode3);
        System.out.println(doubleNode.value);
    }


    public static Node reverseDoubleList(Node head) {
        Node node = null;
        while (head != null) {
            Node next = head.next;
            head.next = node;
            node = head;
            head = next;
        }
        return node;
    }


    public static class Node {
        public int value;
        public Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
