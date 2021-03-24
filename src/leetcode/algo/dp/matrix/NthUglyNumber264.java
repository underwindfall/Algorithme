package leetcode.algo.dp.matrix;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode-cn.com/problems/ugly-number-ii/
public class NthUglyNumber264 {
    // 1. subproblem (:i) 是不是丑数
    // 2. guessing
    // i是丑数-> i*2 i*3 i*5 都是丑数
    // i不是丑数-> i*any 都不是丑数
    // 3. recurrsion
    // dp(i) -> dp[i] 表示第 i + 1 个丑数
    // dp[i + 1] = Math.min(dp[a] * 2, dp[b] * 3, dp[c] * 5 )
    class Dp {
        public int nthUglyNumber(int n) {
            if (n < 1) {
                return Integer.MIN_VALUE;
            }

            int two = 0, three = 0, five = 0;
            int[] dp = new int[n];
            dp[0] = 1;

            for (int i = 1; i < n; ++i) {
                int t1 = dp[two] * 2, t2 = dp[three] * 3, t3 = dp[five] * 5;
                dp[i] = Math.min(Math.min(t1, t2), t3);

                if (dp[i] == t1) {
                    ++two;
                }
                if (dp[i] == t2) {
                    ++three;
                }
                if (dp[i] == t3) {
                    ++five;
                }
            }

            return dp[n - 1];
        }
    }

    class Heap {
        public int nthUglyNumber(int n) {
            Queue<Long> queue = new PriorityQueue<Long>();
            int count = 0;
            long result = 1;
            queue.add(result);
            while (count < n) {
                result = queue.poll();
                // 删除重复的
                while (!queue.isEmpty() && result == queue.peek()) {
                    queue.poll();
                }
                count++;
                queue.offer(result * 2);
                queue.offer(result * 3);
                queue.offer(result * 5);
            }
            return (int) result;
        }

    }

    class BruteForce {
        public int nthUglyNumber(int n) {
            if (n <= 6)
                return n;
            int count = 6, i = 7;
            while (true) {
                if (condition(i)) {
                    ++count;
                }
                if (count == n)
                    break;
                ++i;
            }
            return count;
        }

        boolean condition(int num) {
            if (num > 0 && num <= 6) {
                return true;
            } else if (num <= 0) {
                return false;
            } else {
                while (num % 2 == 0)
                    num /= 2;
                while (num % 3 == 0)
                    num /= 3;
                while (num % 5 == 0)
                    num /= 5;
                return num == 1;
            }
        }
    }
}
