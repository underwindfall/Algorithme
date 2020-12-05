package algo4;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Stack1_3_1_6 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> in = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer integer : in) {
            stack.push(integer);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    class FixedCapacityStackOfStrings {
        int size;
        private String[] a;

        FixedCapacityStackOfStrings(int size) {
            this.size = size;
            a = new String[size];
        }

        public boolean isEmpty() {
            return a.length == 0;
        }

        public int size() {
            return size;
        }

        public void push(String item) {
            a[size++] = item;
        }

        public String pop() {
            return a[--size];
        }
    }

    class ResizingArrayStack<Item> implements Iterable<Item> {
        private Item[] a = (Item[]) new Object[1];// 栈元素
        private int N = 0;

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        private void resize(int max) {
            // 将栈移动到一个大小为max 的新数组
            Item[] temp = (Item[]) new Object[max];
            for (int i = 0; i < N; i++) {
                temp[i] = a[i];
            }
            a = temp;
        }

        public void push(Item item) {
            // 将元素添加到栈顶
            if (N == a.length) {
                resize(2 * a.length);
            }
            a[N++] = item;
        }

        public Item pop() {
            // 从栈顶删除元素
            Item item = a[--N];
            a[N] = null; // 避免对象游离（请见1.3.2.4 节）
            if (N > 0 && N == a.length / 4)
                resize(a.length / 2);
            return item;
        }

        public Iterator<Item> iterator() {
            return new ReverseArrayIterator();
        }

        private class ReverseArrayIterator implements Iterator<Item> { // 支持后进先出的迭代
            private int i = N;

            public boolean hasNext() {
                return i > 0;
            }

            public Item next() {
                return a[--i];
            }

            public void remove() {
            }
        }
    }
}
