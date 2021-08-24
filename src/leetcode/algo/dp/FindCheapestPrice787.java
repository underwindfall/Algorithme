package leetcode.algo.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/
public class FindCheapestPrice787 {

    // time
    // space
    class Dijkstra {
        
        public int findCheapestPrices(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
            for (int[] f : flights) {
                int from = f[0];
                int to = f[1];
                int price = f[2];
                if (!prices.containsKey(from)) {
                    prices.put(from, new HashMap<>());
                } 
                    prices.get(from).put(to, price);
                
            }

            Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
            pq.add(new int[] { 0, src, k + 1 });
            while (!pq.isEmpty()) {
                int[] top = pq.poll();
                int price = top[0];
                int city = top[1];
                int stops = top[2];
                if (city == dst)
                    return price;
                if (stops > 0) {
                    Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                    for (int a : adj.keySet()) {
                        pq.add(new int[] { price + adj.get(a), a, stops - 1 });
                    }
                }
            }
            return -1;

        }
    }

    // time O(k * n^2)
    // space O(k * n^2)
    class DP {
        private static final int MAX = 100007;

        // dp(i, j) 恰好i次航班， j 出发城市
        // dp(i, j) = min(dp(i - 1, j), dp(i - 1, k) + cost(k, j))
        public int findCheapestPrices(int n, int[][] flights, int src, int dst, int k) {
            int[][] dp = new int[k + 2][n];
            for (int i = 0; i < k + 2; i++) {
                Arrays.fill(dp[i], MAX);
            }
            dp[0][src] = 0;
            for (int t = 0; t <= k + 1; t++) {
                for (int i = 0; i < flights.length; i++) {
                    int from = flights[i][0];
                    int to = flights[i][1];
                    int price = flights[i][2];
                    dp[t][to] = Math.min(dp[t][to], dp[t - 1][from] + price);
                }
            }

            int ans = MAX;
            for (int i = 1; i <= k + 1; i++) {
                ans = Math.min(ans, dp[i][dst]);
            }
            return ans == MAX ? -1 : ans;
        }
    }

    // time
    // space
    class DFS {
        private static final int MAX = 100007;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int[][] memo = new int[n][k + 2];
            int res = dfs(flights, src, dst, k + 1, memo);
            return res >= MAX ? -1 : res;
        }

        int dfs(int[][] flights, int src, int dst, int k, int[][] memo) {
            if (k < 0) {
                return MAX;
            }

            if (src == dst) {
                return 0;
            }

            if (memo[src][k] != 0) {
                return memo[src][k];
            }

            int ans = MAX;
            for (int i = 0; i < flights.length; i++) {
                int from = flights[i][0];
                int to = flights[i][1];
                int price = flights[i][2];
                if (from == src) {
                    ans = Math.min(ans, dfs(flights, to, dst, k - 1, memo) + price);
                }
            }
            memo[src][k] = ans;
            return ans;
        }
    }
}
