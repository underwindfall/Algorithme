package leetcode.algo.dp.maxnumNeigbors;

// https://leetcode-cn.com/problems/pizza-with-3n-slices/
public class PizzaSlices1388 {
  /**
     * 方法1：动态规划，线性dp； 与打家劫舍2 相似
     * 
     *      每个人 n / 3 块披萨，
     *      由于是循环的，将披萨拆分成[1, n -1] 与 [2, n]，
     *      分别求在其闭区间上取 n / 3 块披萨的最大值
     *      
     *      dp[i][j]表示前i个披萨，总共取了不间隔的j块披萨的最大值
     *
     * @param slices
     * @return
     */
    public int maxSizeSlices(int[] slices) {
        if (slices == null || slices.length == 0) return 0;
        if (slices.length == 1) return slices[0];
        if (slices.length == 2) return Math.max(slices[0], slices[1]);

        int len = slices.length;
        int n = len / 3;
        return Math.max(maxTakebyRange(slices, 0, len - 2, n), maxTakebyRange(slices, 1, len - 1, n));
    }

    //在闭区间[start,end]取n/3块不间隔披萨最大值
    private int maxTakebyRange(int[] slices, int start, int end, int n) {
        if (start == end) return slices[start];
        int len = end - start + 1;

        //dp[i][j] = the max size we can get, range: [0, i], picked count: j
        int[][] dp = new int[len][n + 1];

        //base condition
        dp[0][0] = 0;
        dp[0][1] = slices[start];
        dp[1][0] = 0;
        dp[1][1] = Math.max(slices[start], slices[start + 1]);

        //dp transfer
        for (int i = 2; i < len; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 2][j - 1] + slices[start + i], dp[i - 1][j]);
            }
        }
        return dp[len - 1][n];
    }
}
