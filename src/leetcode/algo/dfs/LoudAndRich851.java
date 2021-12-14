package leetcode.algo.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// https://leetcode-cn.com/problems/loud-and-rich/solution/F
public class LoudAndRich851 {
    // time O(m + n)
    // space O(m +n)

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        /**
         * ai > bi
         * 将b-> a 构成一个有向图
         */
        for (int[] r : richer) {
            graph.get(r[1]).add(r[0]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            dfs(i, quiet, graph, ans);
        }

        return ans;
    }

    void dfs(int index, int[] quiet, List<List<Integer>> graph, int[] ans) {
        if (ans[index] != -1) {
            return;
        }
        ans[index] = index;
        for (int next : graph.get(index)) {
            dfs(next, quiet, graph, ans);
            if (quiet[ans[next]] < quiet[ans[index]])  {
                ans[index] = ans[next];
            }
        }
    }
}
