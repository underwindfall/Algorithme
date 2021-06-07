package leetcode.algo.dp.prefix;

//https://leetcode-cn.com/problems/last-stone-weight-ii
public class LastStoneWeightII1049 {
    // This question eaquals to partition an array into 2 subsets whose difference is minimal
// (1) S1 + S2  = S
// (2) S1 - S2 = diff  

// ==> -> diff = S - 2 * S2  ==> minimize diff equals to  maximize S2 

// Now we should find the maximum of S2 , range from 0 to S / 2, using dp can solve this

// dp[i][j]   = {true if some subset from 1st to j'th has a sum equal to sum i, false otherwise}
    // i ranges from (sum of all elements) {1..n}
    // j ranges from  {1..n}
    class DP {
        public int lastStoneWeightII(int[] stones) {
            int sum = 0;
            for (int weight : stones) {
                sum += weight;
            }
            int n = stones.length, m = sum / 2;
            boolean[][] dp = new boolean[n + 1][m + 1];
            dp[0][0] = true;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j <= m; ++j) {
                    if (j < stones[i]) {
                        dp[i + 1][j] = dp[i][j];
                    } else {
                        dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                    }
                }
            }
            for (int j = m;; --j) {
                if (dp[n][j]) {
                    return sum - 2 * j;
                }
            }
        }
    }

    class Recursive {

    }
}
