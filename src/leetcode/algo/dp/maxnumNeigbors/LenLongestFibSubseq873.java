package leetcode.algo.dp.maxnumNeigbors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode-cn.com/problems/length-of-longest-fibonacci-subsequence/
public class LenLongestFibSubseq873 {

    //思路是利用fib特性实现这个安利判断
    class Hash {
        public int lenLongestFibSubseq(int[] A) {
            if (A.length < 3) {
                return 0;
            }
            int N = A.length;
            Set<Integer> S = new HashSet<>();
            for (int x : A)
                S.add(x);
            int ans = 0;
            // f(c) = f(i) + f(j)
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    int fib = A[i] + A[j];
                    int prev = A[j];
                    int length = 2;
                    while (S.contains(fib)) {
                        int tmp = fib;
                        fib = prev + fib;
                        prev = tmp;
                        length++;
                        ans = Math.max(ans, length);
                    }
                }
            }
            return ans;
        }
    }

    class Dp {
        public int lenLongestFibSubseq(int[] A) {
            if (A.length < 3) {
                return 0;
            }
            int[][] dp = new int[A.length - 1][A.length];
            for (int[] d : dp) { // 将dp的每个值初始化为2
                Arrays.fill(d, 2);
            }
            int maxLen = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < A.length; i++) {
                map.put(A[i], i);
            }
            for (int i = 0; i < A.length - 1; i++) {
                for (int j = i + 1; j < A.length; j++) {
                    int k = A[j] - A[i];
                    if (map.containsKey(k) && map.get(k) < i) {
                        dp[i][j] = dp[map.get(k)][i] + 1;
                        maxLen = Math.max(maxLen, dp[i][j]);
                    }
                }
            }
            return maxLen;
        }
    }

    // 思路
    // 首先题目说了Fib 子序列要>= 3， 可以判断 最小值就是2
    // dp 这里表达的意思是从 i - j， 能够组成的Fib数列的长度是dp value
    // 1,2(k),3(i),5,8(j)
    // dp[i,j] = Math.max(dp[i,j] , dp[k,i] +1)
    // time O(N^3)
    // espace O(N*N)
    @Deprecated
    public int lenLongestFibSubseq(int[] arr) {
        int MAX = Integer.MIN_VALUE;
        int n = arr.length;
        if (n < 3) {
            return 0;
        }
        int[][] dp = new int[n - 1][n];
        for (int[] d : dp) { // 将dp的每个值初始化为2
            Arrays.fill(d, 2);
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i - 1; k >= 0; k--) {
                    if (arr[k] + arr[i] == arr[j]) {
                        dp[i][j] = Math.max(dp[i][j], dp[k][i] + 1);
                    }
                }
                MAX = Math.max(MAX, dp[i][j]);
            }
        }
        return MAX == 2 ? 0 : MAX;
    }
}
