package swordoffer;

import java.util.PriorityQueue;

//https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
public class LCOF41 {

    //time O(logn)
    //space O(n)
    class MedianFinder {

        /** initialize your data structure here. */
        PriorityQueue<Integer> pMin = new PriorityQueue<>((a, b) -> (a - b));
        PriorityQueue<Integer> pMax = new PriorityQueue<>((a, b) -> (b - a));

        public MedianFinder() {

        }

        /**
         * pmin 存数组中较大的部分 peek是较大部份的最小值
         * pmax 存数组中较小的部分 peek是较小部份的最大值
         */
        public void addNum(int num) {
            if (pMin.size() == pMax.size()) {
                pMax.add(num);
                pMin.add(pMax.poll());
            } else {
                pMin.add(num);
                pMax.add(pMin.poll());
            }
        }

        public double findMedian() {
            return pMin.size() == pMax.size() ? (pMax.peek() + pMin.peek()) / 2.0 : pMin.peek();
        }
    }
}
