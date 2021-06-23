package leetcode.algo.dp;

import java.util.List;

//https://leetcode-cn.com/problems/number-of-paths-with-max-score/
public class PathsWithMaxScore1301 {
    // time O(N^2)
    // espace O(N^2)
    // 最大得分数 
    //                  右              下            右下
    // dp(x, y) = max(dp(x+1, y), dp(x, y + 1), dp(x+1, y+1)) + board[x, y]
    // 针对 最大得分方案数
    //需要注意，最大值不一定是由一个位置得出。
    //如果有多个位置同时能取到最大得分，那么方案数应该是多个位置的方案数之和。
    
    int INF = Integer.MIN_VALUE;
    int mod = (int)1e9+7;
    int n;
    
    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();

        char[][] cs = new char[n][n];

        for (int i = 0; i < n; i++) {
            cs[i] = board.get(i).toCharArray();
        }

        // f(i) 代表从右下角到位置 i 的最大得分
        int[] f = new int[n * n];
        // g(i) 代表右下角到位置i 并取得最大得分的方案数
        int[] g =   new int[n * n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j --) {
                int idx = getIdx(i, j);

                //一个初始化状态 如果是在最后一格（起点）
                // g[idx] = 1 : 代表到达起点的路径只有一条，这样我们就有了一个「有效值」可以滚动下去
                // f[idx] = 0 : 代表在起点得分为 0
                if (i == n - 1 && j == n - 1) {
                    g[idx] = 1;
                    continue;
                }

                // 如果该位置是「障碍点」，那么对应状态为：
                // g[idx] = 0   : 「障碍点」不可访问，路径为 0
                // f[idx] = INF : 「障碍点」不可访问，得分为无效值
                if (cs[i][j] == 'X') {
                    f[idx] = INF;
                    continue;
                }
                // 如果是第一个格子（终点），这时候位置得分为 0
                int val = (i == 0 && j == 0) ? 0 : cs[i][j] - '0';
                // u 代表当前位置的「最大得分」；t 代表取得最大得分的「方案数」
                int u = INF, t = 0;
                // 如果「下方格子」合法，尝试从「下方格子」进行转移
                if (i + 1 < n) {    
                    int cur = f[getIdx(i + 1, j)] + val;
                    int cnt =  g[getIdx(i + 1, j)];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0]; t = res[1];
                }

                 // 如果「右边格子」合法，尝试从「右边格子」进行转移
                if (j + 1 < n) {
                    int cur = f[getIdx(i, j + 1)] + val;
                    int cnt = g[getIdx(i, j + 1)];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0]; t = res[1];
                }

                // 如果「右下角格子」合法，尝试从「右下角格子」进行转移
                if (i + 1 < n && j + 1 < n) {
                    int cur = f[getIdx(i + 1, j + 1)] + val;
                    int cnt = g[getIdx(i + 1, j + 1)];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0]; t = res[1];
                }
                // 更新 dp 值
                f[idx] = u < 0 ? INF : u;
                g[idx] = t;
            }
        }


        // 构造答案
        int[] ans = new int[2];
        // 如果终点不可达（动规值为 INF）时，写入 0
        ans[0] = f[getIdx(0, 0)] == INF ? 0 : f[getIdx(0, 0)];
        // 如果终点不可达（动规值为 INF）时，写入 0
        ans[1] = f[getIdx(0, 0)] == INF ? 0 : g[getIdx(0, 0)];
        return ans;
    }

    //更新dp值
    int[] update(int cur, int cnt, int u, int t) {
        /// 起始答案为 [u, t] : u 为「最大得分」，t 为最大得分的「方案数」
        int[] ans = new int[]{u, t};
        // 如果当前值大于 u，更新「最大得分」和「方案数」
        if (cur > u) {
            ans[0] = cur;
            ans[1] = cnt;
        }
        // 如果当前值等于 u，增加「方案数」
        else if (cur == u && cur != INF) {
            ans[1] += cnt;
        }
        ans[1] %= mod;
        return ans;
    }

    // 二维坐标 (x,y) 与 idx 的相互转换
    int getIdx(int x, int y) {
        return x * n + y;
    }

}
