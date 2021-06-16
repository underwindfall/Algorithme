package leetcode.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/pacific-atlantic-water-flow/
public class PacificAtlantic417 {

    /**
     * 思路是分别从pacific atlantic 遍历
     * 
     * 从底到高 遍历 如果邻居的高度 >= 出发的高度 则可以 递归 否则return
     *
     * time O(M * N)
     * 
     * espace O(M * N)
     */

    boolean[][] pVisited;
    boolean[][] aVisited;
    List<List<Integer>> result;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        result = new ArrayList<>();
        int M = heights.length, N = heights[0].length;
        pVisited = new boolean[M][N];
        aVisited = new boolean[M][N];
        // 左右 + 上下两边dfs
        for (int i = 0; i < M; i++) { // left right
            dfs(heights, pVisited, i, 0);
            dfs(heights, aVisited, i, N - 1);
        }
        //
        for (int j = 0; j < N; j++) { // top bottom
            dfs(heights, pVisited, 0, j);
            dfs(heights, aVisited, M - 1, j);
        }
        return result;
    }

    void dfs(int[][] heights, boolean[][] visited, int row, int col) {
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        // 大西洋和太平洋 都遍历过这点 满足条件
        if (pVisited[row][col] && aVisited[row][col]) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(row);
            tmp.add(col);
            result.add(tmp);
        }

        if (row - 1 >= 0 && heights[row - 1][col] >= heights[row][col]) {
            dfs(heights, visited, row - 1, col);
        }

        if (row + 1 < heights.length && heights[row + 1][col] >= heights[row][col]) {
            dfs(heights, visited, row + 1, col);
        }

        if (col - 1 >= 0 && heights[row][col - 1] >= heights[row][col]) {
            dfs(heights, visited, row, col - 1);
        }

        if (col + 1 < heights[0].length && heights[row][col + 1] >= heights[row][col]) {
            dfs(heights, visited, row, col + 1);
        }
    }
}
