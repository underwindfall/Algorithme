package leetcode.algo.dp;

// https://leetcode-cn.com/problems/paint-house-iii/
public class PaintHouseIII1473 {
    // 1.subProblem
    // 子问题 [0.....i'...i] 个房子 i'可以根据 i'的颜色来决定之后 i'+ 1 的house 会不会让社区K+1
    // 2.guessing
    //  [0.....i'...i] 有几种情况
    //      - i' house[i'] != 0 意味着他已经有颜色了 
    //             此时 i' + 1 如果有颜色 
    //             如果      i' + 1 ==  i'  社区不会增加
    //             如果      i' + 1  !=  i'  社区 + 1  
    //      - i' house[i'] == 0 意味着他没有颜色了
    //             此时 i' + 1 如果有颜色 
    //              i' == i'+ 1 颜色相同的话   社区不增加 cost 需要累加
    //              i' != i'+ 1 颜色不同的话   社区 + 1 cost 累加
    // 3.recurrence
    // dp[i][j][k] i 个房子 j个颜色 k个target 所需要喷漆的最小话费值
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // dp[i][j][k] i 个房子 j个颜色 k个target
        int[][][] dp = new int[m][n + 1][target + 1];
        // 初始化所有的房子值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n + 1; j++) {
                for (int k = 0; k < target + 1; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        // 遍历所有初始房子中 不为0 的参数 和为0的参数
        // 初始化第0个房子
        // 第0个房子没涂色
        if (houses[0] == 0) {
            for (int j = 1; j <= n; j++) {
                dp[0][j][0] = cost[0][j - 1];
            }
        } else {
            // 第0个房子已经涂色
            dp[0][houses[0]][0] = 0;
        }
        //因为第一个房子已经给过初始值了所以暂时忽略
        // 状态转移时涂色完第i个房子。
        for (int i = 1; i < m; i++) {
            // 最多j中颜色
            for (int j = 1; j <= n; j++) {
                //最多target社区
                for (int k = 0; k < target; k++) {
                    //如果此时的房子没有颜色 -->   - i' house[i'] == 0 意味着他没有颜色了
                    if (houses[i] == 0) {
                        for (int j_old = 1; j_old <= n; j_old++) {
                            //如果之前的房子还没被复植 暂时不看
                            if (dp[i - 1][j_old][k] == Integer.MAX_VALUE)
                                continue;
                            //此时要上色的房子和之前的房子颜色相同的话  -->      i' == i'+ 1 颜色相同的话   社区bu增加 cost 需要累加 
                            if (j_old == j) {
                                dp[i][j][k] = Math.min(dp[i - 1][j_old][k] + cost[i][j - 1], dp[i][j][k]);
                            } else {
                                // i' != i'+ 1 颜色不同的话   社区 + 1 cost 累加
                                dp[i][j][k + 1] = Math.min(dp[i - 1][j_old][k] + cost[i][j - 1], dp[i][j][k + 1]);
                            }
                        }
                    } 
                    // i' house[i'] != 0 意味着他已经有颜色了 
                    else {
                        if (dp[i - 1][j][k] == Integer.MAX_VALUE)
                            continue;
                            // 此时 i' + 1 如果有颜色 
                            //             如果      i' + 1 ==  i'  社区不会增加
                            if (houses[i] == j) {
                                dp[i][j][k] = Math.min(dp[i - 1][j][k], dp[i][j][k]);
                            } else {
                                // 如果      i' + 1  !=  i'  社区 + 1  
                                dp[i][houses[i]][k + 1] = Math.min(dp[i - 1][j][k], dp[i][houses[i]][k + 1]);
                            }
                    }
                }
            }
        }



        int ans = Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++) {
            ans = Math.min(ans, dp[m - 1][j][target - 1]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
