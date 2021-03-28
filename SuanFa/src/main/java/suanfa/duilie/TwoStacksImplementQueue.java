package suanfa.duilie;

import java.util.Stack;

public class TwoStacksImplementQueue {

    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        // push栈向pop栈倒入数据
        private void pushToPop() {
            if (!stackPop.isEmpty()) {
                return;
            }
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }

        public void add(int pushInt) {
            stackPush.push(pushInt);
        }

        public int poll() {
            pushToPop();
            if (stackPop.isEmpty()) {
                throw new RuntimeException("空了！！！");
            }
            return stackPop.pop();
        }

        public int peek() {
            pushToPop();
            if (stackPop.isEmpty()) {
                throw new RuntimeException("空了！！！");
            }
            return stackPop.peek();
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

}
