package leetcode.datastructure.stack.monotoinic;

import java.util.LinkedList;
import java.util.Stack;

// https://leetcode-cn.com/problems/online-stock-span/
public class OnlineStockSpan901 {

    // time O(n)
    // space O(n)
    class MonotonicStack {
        Stack<Integer> prices, weights;

        public MonotonicStack() {
            prices = new Stack<>();
            weights = new Stack<>();
        }

        public int next(int price) {
            int w = 1;
            while (!prices.isEmpty() && prices.peek() < price) {
                prices.pop();
                w += weights.pop();
            }
            prices.push(price);
            weights.push(w);
            return w;
        }
    }

    // TLE
    // time O(n)
    // space O(n)
    class BruteForceStockSpanner {
        LinkedList<Integer> list;

        public BruteForceStockSpanner() {
            list = new LinkedList<>();
        }

        public int next(int price) {
            list.addLast(price);
            int res = 0;
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i) <= price) {
                    res++;
                } else {
                    break;
                }
            }
            return res;
        }
    }
}
