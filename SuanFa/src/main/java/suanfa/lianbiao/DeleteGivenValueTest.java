package suanfa.lianbiao;

public class DeleteGivenValueTest {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}


	public static Node removeValue(Node head, int num) {
		while (head  !=  null){
			if (head.value ==  num) {
				head  =  head.next;
			}else{
				break;
			}
		}
		Node pre = head;
		Node cur = head;
		while (cur!= null){
			if(cur.value == num){
				pre.next =cur.next;
			}else{
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}
}
