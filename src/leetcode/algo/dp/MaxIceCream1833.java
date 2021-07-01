package leetcode.algo.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode-cn.com/problems/maximum-ice-cream-bars/
public class MaxIceCream1833 {
    class DP {
        // dp 背包问题
        // dp(i, j) i 第 i 只雪糕 还剩j 块钱 购买的雪糕数量
        // dp(i, j) = max(dp(i,j), dp(i - 1, j - costs[i]) + 1) j >= cost[i]
        // time O(N * coins)
        // espace O(N * coins)
        public int maxIceCream(int[] costs, int coins) {
            int[][] dp = new int[costs.length][coins + 1];
            for (int i = 1; i < coins + 1; i++) {
                if (i >= costs[0])
                    dp[0][i] = 1;
            }
            for (int i = 1; i < costs.length; i++) {
                for (int j = 1; j <= coins; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= costs[i])
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - costs[i]] + 1);
                }
            }
            return dp[costs.length - 1][coins];
        }
    }

    // 贪心
    // 如果用coins 的钱 买costs 中k 个雪糕 ，将其中一只雪糕代替，则超过
    // 假设购买最便宜的雪糕，在总价格不超过 coins 的情况下最多可以购买 k 支雪糕。如果将 k
    // 支最便宜的雪糕中的任意一支雪糕替换成另一支雪糕，则替换后的雪糕的价格大于或等于替换前的雪糕的价格，
    // 因此替换后的总价格大于或等于替换前的总价格，允许购买的雪糕数量不可能超过 k。因此可以买到的雪糕的最大数量为 k.
    // time O(N * logN)
    // espace O(logN)
    class Greedy {
        public int maxIceCream(int[] costs, int coins) {
            Arrays.sort(costs);
            int amount = 0;
            for (int cost : costs) {
                if (coins >= cost) {
                    coins -= cost;
                    amount++;
                } else {
                    break;
                }
            }

            return amount;
        }
    }

    // time O(logN*N)
    // espace O(N)
    // 利用大根堆记录costs数组中较小的值，同时记录当前堆的总价格并与cost作比较。
    // 1.sum为当前冰激凌总价值，与cost作比较，如果小于等于cost，将其放入堆中；
    // 2.如果大于cost，sum减去该冰激凌价格，同时判断堆的跟是否比该冰激凌价格更贵；
    // 3.如果有，sum减去其价格，从堆中删除，将较小值放入队中，并记录其价格，如此遍历；
    // 4.返回堆的规模。
    class Heap {
        public int maxIceCream(int[] costs, int coins) {
            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });

            int sum = 0;
            for (int i : costs) {
                sum += i;
                if (sum <= coins) {
                    queue.offer(i);
                } else {
                    sum -= i;
                    if (!queue.isEmpty() && i <= queue.peek()) {
                        sum -= queue.peek();
                        queue.poll();
                        queue.offer(i);
                        sum += i;
                    }
                }
            }
            return queue.size();
        }
    }
}
