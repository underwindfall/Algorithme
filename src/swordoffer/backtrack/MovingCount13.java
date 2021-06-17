package swordoffer.backtrack;

//https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
public class MovingCount13 {
    //time O(N*M)
    //espace O(N*M)
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int count = 0;

        dfs(0, 0, k, visited);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    void dfs(int row, int col, int k, boolean[][] visited) {
        if (!inArea(row, col, visited)) {
            return;
        }

        if (visited[row][col]) {
            return;
        }

        if (sum(row) + sum(col) > k) {
            return;
        }

        visited[row][col] = true;
        dfs(row - 1, col, k, visited);
        dfs(row + 1, col, k, visited);
        dfs(row, col - 1, k, visited);
        dfs(row, col + 1, k, visited);
    }

    boolean inArea(int row, int col, boolean[][] visited) {
        return row >= 0 && row < visited.length && col >= 0 && col < visited[0].length;
    }

    int sum(int x) {
        if (x == 100)
            return 1;
        if (x <= 9)
            return x;
        return x / 10 + x % 10;
    }
}
