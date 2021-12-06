package leetcode.algo.binarysearch;

import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/sell-diminishing-valued-colored-balls/
public class SellDiminishingBalls1648 {
    // time O(logN)
    // space O(n)
    class PQ {
        private final static int MOD = (int) (1e9) + 7;

        public int maxProfit(int[] inventory, int orders) {
            // orders
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> (o2 - o1));
            for (int i : inventory) {
                pq.add(i);
            }
            int res = 0;
            while (orders > 0) {

                int max = pq.poll();

                res += max;
                res = res % (MOD);
                orders--;
                if (max - 1 >= 0) {
                    pq.add(max - 1);
                }

            }
            return res;
        }
    }

    class BS {
        static final int MOD = 1000000007;

        /**
         * orders = 12
         * invents = [6, 10, 10]
         * 1 2 3 4 5 6 use 1
         * 1 2 3 4 5 6 7 8 9 10 use 5
         * 1 2 3 4 5 6 7 8 9 10 use 6
         *         |
         * nums[costs >=5] = 14
         * nums[costs >=6] = 11
         * 所以找到第五列，随便选择12 - 11 个5 补齐
         */
        public int maxProfit(int[] inventory, int orders) {
            int left = 0, right = findMax(inventory);
            while (left < right) {
                int mid = ((right - left) / 2 + left + 1);
                if (getOrders(mid, inventory) >= orders) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            int totalOrders = getOrders(left, inventory);
            return getProfit(left, inventory) - ((totalOrders - orders) * left);
        }

        int getProfit(int k, int[] inventory) {
            int sum = 0;
            for (int i : inventory) {
                if (i >= k) {
                    sum += ((k + i) * (i - k + 1) / 2);
                }
            }
            return sum;
        }

        int getOrders(int k, int[] inventory) {
            int orders = 0;
            for (int i : inventory) {
                if (i >= k) {
                    orders += (i - k + 1);
                }
            }
            return orders;
        }

        int findMax(int[] inventory) {
            int max = 0;
            for (int i : inventory) {
                max = Math.max(max, i);
            }
            return max;

        }
    }
}
