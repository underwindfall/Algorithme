package leetcode.algo.backtrack;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/number-of-distinct-islands/
public class NumDistinctIslands694 {
    // time O(n * m)
    // space O(n * m)
    Set<String> set = new HashSet<>();

    public int numDistinctIslands(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuffer sb = new StringBuffer();
                    dfs(grid, i, j, 0, 0, sb);
                    if (!set.contains(sb.toString())) {
                        set.add(sb.toString());
                    }
                }
            }
        }
        return set.size();
    }

    public void dfs(int[][] grid, int i, int j, int x, int y, StringBuffer sb) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            grid[i][j] = 0;
            sb.append(x);
            sb.append(y);
            dfs(grid, i - 1, j, x - 1, y, sb);
            dfs(grid, i, j + 1, x, y + 1, sb);
            dfs(grid, i + 1, j, x + 1, y, sb);
            dfs(grid, i, j - 1, x, y - 1, sb);
        }
    }
}
