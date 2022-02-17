package swordoffer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
public class LCOF59_II {
    // time O(n)
    // space O(1)
    class MaxQueue {

        Queue<Integer> q;
        Deque<Integer> d;

        public MaxQueue() {
            q = new LinkedList<>();
            d = new LinkedList<>();
        }

        public int max_value() {
            return d.isEmpty() ? -1 : d.peekFirst();
        }

        /**
         * 为了不让d收到peekFirst的影响，要保证d是个单调递减的Queue
         * 
         * 只要保证peekLast> value时候才插入
         */
        public void push_back(int value) {
            while (!d.isEmpty() && d.peekLast() < value) {
                d.pollLast();
            }
            d.offerLast(value);
            q.offer(value);
        }

        public int pop_front() {
            if (q.isEmpty())
                return -1;
            int value = q.poll();
            if (value == d.peekFirst()) {
                d.pollFirst();
            }
            return value;
        }
    }
}
