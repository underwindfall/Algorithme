package leetcode.algo.dp.digits;

//https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/
public class FindIntegers600 {

    // time O(log(nMax))
    // space O(log(nMax))
    class DP {
        public int findIntegers(int n) {
            int[] dp = new int[31];
            dp[0] = dp[1] = 1;
            for (int i = 2; i < 31; ++i) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
    
            int pre = 0, res = 0;
            for (int i = 29; i >= 0; --i) {
                int val = 1 << i;
                if ((n & val) != 0) {
                    res += dp[i + 1];
                    if (pre == 1) {
                        break;
                    }
                    pre = 1;
                } else {
                    pre = 0;
                }
    
                if (i == 0) {
                    ++res;
                }
            }
    
            return res;
        }
    
    }

    /**
     * 0 结尾
     * 
     * 补足0 补足1
     * 
     * 
     * 1 结尾
     * 
     * 补足0
     */
    class DFS {
        int res = 1;// dfs从1开始,n=0 返回1
        int end;

        public int FindIntegers(int n) {
            end = n;
            dfs(1);
            return res;
        }

        void dfs(int curr) {
            if (curr > end)
                return;
            res++;
            if ((curr & 1) == 1) {
                dfs(curr << 1);
            } else {
                dfs(curr << 1);
                dfs((curr << 1) + 1);
            }
            return;
        }
    }
}
