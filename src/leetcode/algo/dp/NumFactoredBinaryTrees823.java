package leetcode.algo.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/binary-trees-with-factors/
public class NumFactoredBinaryTrees823 {

    // time O(N^2)
    // espace O(N)
    class DP {
        private static final int MOD = (int) (1e9 + 7);

        public int numFactoredBinaryTrees(int[] arr) {
            int N = arr.length;
            Arrays.sort(arr);
            long[] dp = new long[N];
            Arrays.fill(dp, 1);

            Map<Integer, Integer> index = new HashMap<>();
            for (int i = 0; i < N; i++) {
                index.put(arr[i], i);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[i] % arr[j] == 0) {
                        int rightChild = arr[i] / arr[j];
                        if (index.containsKey(rightChild)) {
                            dp[i] = (dp[i] + dp[j] * dp[index.get(rightChild)]) % MOD;
                        }
                    }
                }
            }

            long result = 0;
            for (long x : dp) {
                result += x;
            }
            return (int) (result % MOD);
        }
    }

    class DFSMemo {
        private static final int MOD = (int) (1e9 + 7);

        int[] arr;
        Map<Integer, Integer> mapNumToIndex;

        public int numFactoredBinaryTrees(int[] arr) {
            mapNumToIndex = new HashMap<>();
            this.arr = arr;
            long[] dp = new long[arr.length];
            Arrays.fill(dp, -1);

            for (int i = 0; i < arr.length; i++) {
                mapNumToIndex.put(arr[i], i);
            }

            long result = 0;
            for (int i = 0; i < arr.length; i++) {
                result += dfs(arr[i], dp);
            }
            return (int) (result % MOD);
        }

        long dfs(int num, long[] dp) {
            if (!mapNumToIndex.containsKey(num))
                return 0;

            int index = mapNumToIndex.get(num);

            if (dp[index] != -1)
                return dp[index];

            long count = 1;

            for (int i = 0; i < arr.length; i++) {
                if (num % arr[i] == 0 && mapNumToIndex.containsKey(num / arr[i])) {
                    count += dfs(arr[i], dp) * dfs(num / arr[i], dp);
                }
            }

            dp[index] = count;

            return count;
        }
    }
}
