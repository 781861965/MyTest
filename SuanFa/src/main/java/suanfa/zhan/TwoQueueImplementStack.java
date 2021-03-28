package suanfa.zhan;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TwoQueueImplementStack {

    public static class TwoQueueStack<T> {
        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueueStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(T value) {
            queue.add(value);
        }

        public T poll() {
            if (isEmpty()) {
                throw new RuntimeException("空了！！！");
            }
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            T t = queue.poll();
            Queue<T> queue1 = queue;
            queue = help;
            help = queue1;
            return t;
        }

        public T peek() {
            if (isEmpty()) {
                throw new RuntimeException("空了！！！");
            }
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            T t = queue.poll();
            help.add(t);
            Queue<T> queue1 = queue;
            queue = help;
            help = queue1;
            return t;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }

}
