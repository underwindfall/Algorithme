package leetcode.algo.dp.matrix;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//https://leetcode-cn.com/problems/super-ugly-number/
public class NthSuperUgly313 {

    // time O(N * logN)
    // espace O(N)
    class Heap {
        public int nthSuperUglyNumber(int n, int[] primes) {
            Set<Long> seen = new HashSet<Long>();
            PriorityQueue<Long> heap = new PriorityQueue<Long>();
            seen.add(1L);
            heap.offer(1L);
            int ugly = 0;
            for (int i = 0; i < n; i++) {
                long curr = heap.poll();
                ugly = (int) curr;
                for (int prime : primes) {
                    long next = curr * prime;
                    if (seen.add(next)) {
                        heap.offer(next);
                    }
                }
            }
            return ugly;
        }
    }

    // 264的升级版
    // dp(i) 是 i + 1 个超级丑数
    // 直到选出第 n 个丑数 dp[n - 1]。基于哪一个超级丑数，可以使用一个长度和 primes 相等的数组 indexes 记录下来，
    // indexes[i] 表示下一个丑数如果选择了 primes[i] 是基于哪一个下标的超级丑数得到
    // time O(N*M)
    // epsace O(N+M)
    class DP {
        public int nthSuperUglyNumber(int n, int[] primes) {
            int pLen = primes.length;
            int[] indexes = new int[pLen];
            int[] dp = new int[n];
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 0; j < pLen; j++) {
                    dp[i] = Math.min(dp[i], dp[indexes[j]] * primes[j]);
                }
                // dp[i] 是之前的哪个丑数乘以对应的 primes[j] 选出来的，给它加 1
                for (int j = 0; j < pLen; j++) {
                    if (dp[i] == dp[indexes[j]] * primes[j]) {
                        // 注意：这里不止执行一次，例如选出 14 的时候，2 和 7 对应的最小丑数下标都要加 1，大家可以打印 indexes 和 dp 的值加以验证
                        indexes[j]++;
                    }
                }
            }
            return dp[n - 1];
        }
    }
}
