package leetcode.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode-cn.com/problems/moving-average-from-data-stream/
public class MovingAverage346 {
    /**
     * 循环链表去执行这个数据结构
     */
    class Circus {
        int[] data;
        int head;
        double sum;
        int count;

        /** Initialize your data structure here. */
        public Circus(int size) {
            data = new int[size];
            head = -1;
            sum = 0;
            count = 0;
        }

        public double next(int val) {
            count++;
            head = (head + 1) % (data.length);
            data[head] = val;
            return avg();
        }

        double avg() {
            sum = 0;
            for (int i : data) {
                sum += i;
            }
            return (double) (sum / Math.min(count, data.length));
        }
    }

    /**
     * 双端队列
     */
    class DequeSolution {
        int size = 0;
        double windowSum = 0, count = 0;

        Deque<Integer> queue = new ArrayDeque<Integer>();

        public DequeSolution(int size) {
            this.size = size;
        }

        public double next(int val) {
            ++count;
            // calculate the new sum by shifting the window
            queue.add(val);
            int tail = count > size ? (int) queue.poll() : 0;
            windowSum = windowSum - tail + val;
            return windowSum / Math.min(size, count);
        }
    }
}
