package training.books;

import java.util.LinkedList;
import java.util.Queue;

public class Queue1_3_1_5 {

    public static int[] readInts(String name) {
        class In {
            In(String name) {
            }

            public Integer readInt() {
                return null;
            }

            boolean isEmpty() {
                return false;
            }
        }
        In in = new In(name);
        Queue<Integer> queue = new LinkedList<>();
        while (!in.isEmpty()) {
            queue.add(in.readInt());
        }
        int N = queue.size();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = queue.poll();
        }
        return a;
    }

}
