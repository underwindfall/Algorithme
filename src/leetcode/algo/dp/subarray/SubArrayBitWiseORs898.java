package leetcode.algo.dp.subarray;

import java.util.HashSet;
import java.util.Set;

//https://leetcode-cn.com/problems/bitwise-ors-of-subarrays/
public class SubArrayBitWiseORs898 {

    // time O(N^2)
    // space O(N^2)
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> res = new HashSet<>();
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                if (l == 1) {
                    dp[i][j] = A[i];
                    continue;
                }
                dp[i][j] = dp[i][j - 1] | A[j];
                res.add(dp[i][j]);
            }
        }
        return res.size();
    }


    // time O(n * log(maxA))
    // space O(n * log(maxA))
    class DpOptimization {
        public int subarrayBitwiseORs(int[] A) {
            Set<Integer> res = new HashSet<>();
            Set<Integer> cur = new HashSet<>();
            for (int a : A) {
                Set<Integer> nxt = new HashSet<>();
                nxt.add(a);
                for (int b: cur) {
                    nxt.add(a | b);
                }
                res.addAll(nxt);
                cur = nxt;
            }
            return res.size();       
        }
    }
}
