package suanfa.shuzu;

public class RingArrayTest {

    public static class MyQueue {
        private int[] arr;
        private int pushi;// end
        private int polli;// begin
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("满了！！！");
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("空了！！！");
            }
            size--;
            int num = arr[polli];
            pushi = nextIndex(polli);
            return num;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return ++i % limit;
        }
    }
}
