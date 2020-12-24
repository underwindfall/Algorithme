package leetcode.algo.dp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// https://leetcode-cn.com/problems/perfect-squares/
// 数学规律
// 最小平方数 = 前一位最小平方数+ 1^2 || 平方数
// 即 dp[i] = MIN(dp[i], dp[i - j * j] + 1)
public class SqrtNumber279 {
    public int numSquares(int n) {
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited.add(n);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int number = q.poll();
                for (int j = 1; j * j <= number; j++) {
                    int temp = number - j * j;
                    if (temp == 0) {
                        return count + 1;
                    }
                    if (!visited.contains(temp)) {
                        q.add(temp);
                        visited.add(temp);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    class Dp {
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                dp[i] = i; // 最坏的情况就是每次+1
                for (int j = 1; i - j * j >= 0; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
                }
            }
            return dp[n];
        }
    }
}
