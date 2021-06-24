package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/path-with-minimum-effort/
public class MinimumEffortPath1631 {
    int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    int ans = Integer.MAX_VALUE;
    int rows;
    int cols;

    public int minimumEffortPath(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        int left = 0;
        int right = 1000000;
        while (left < right) {
            int mid = (right + left) / 2;
            // 更新左右边界
            if (dfs(heights, 0, 0, mid, new boolean[rows][cols])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // dfs寻找是否存在一条连通左上角和右下角结点的道路
    boolean dfs(int[][] heights, int row, int col, int mid, boolean[][] visited) {
        if (row == rows - 1 && col == cols - 1) {
            return true;
        }
        visited[row][col] = true;
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol]
                    && Math.abs(heights[newRow][newCol] - heights[row][col]) <= mid) {
                if (dfs(heights, newRow, newCol, mid, visited))
                    return true;
            }
        }
        return false;
    }
}
