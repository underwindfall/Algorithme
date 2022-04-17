package leetcode.algo.bfs;

// https://leetcode-cn.com/problems/game-of-life/
public class GameOfLife289 {

    // time O(nm)
    // space O(nm)
    class CopyArr {
        public void gameOfLife(int[][] board) {
            // live 1 dead 0
            // 1. 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
            // 2. 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
            // 3. 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
            // 4. 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
            int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 },
                    { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };

            int rows = board.length, cols = board[0].length;
            int[][] copyBoard = new int[rows][cols];
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    copyBoard[row][col] = board[row][col];
                }
            }

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int liveNeighbors = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            for (int[] dir : dirs) {
                                int dx = dir[0], dy = dir[1];
                                int newR = row + dx;
                                int newC = col + dy;
                                if (newR >= 0 && newR < rows && newC >= 0 && newC < cols
                                        && copyBoard[newR][newC] == 1) {
                                    liveNeighbors++;
                                }
                            }
                        }
                    }

                    // rule 1 or rule 3
                    if (copyBoard[row][col] == 1 && liveNeighbors < 2 || liveNeighbors > 3) {
                        board[row][col] = 0;
                    }
                    // rule 4
                    if (copyBoard[row][col] == 0 && liveNeighbors == 2) {
                        board[row][col] = 1;
                    }
                }
            }
        }
    }

    // time O(n * m)
    // space O(1)
    class MultiState {
        // 规则 1：如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡。这时候，将细胞值改为 -1，代表这个细胞过去是活的现在死了；

        // 规则 2：如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活。这时候不改变细胞的值，仍为 1；

        // 规则 3：如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡。这时候，将细胞的值改为 -1，代表这个细胞过去是活的现在死了。可以看到，因为规则 1
        // 和规则 3 下细胞的起始终止状态是一致的，因此它们的复合状态也一致；

        // 规则 4：如果死细胞周围正好有三个活细胞，则该位置死细胞复活。这时候，将细胞的值改为 2，代表这个细胞过去是死的现在活了。
        public void gameOfLife(int[][] board) {
            int[] neighbors = { 0, 1, -1 };

            int rows = board.length;
            int cols = board[0].length;

            // 遍历面板每一个格子里的细胞
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {

                    // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                    int liveNeighbors = 0;

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {

                            if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                                // 相邻位置的坐标
                                int r = (row + neighbors[i]);
                                int c = (col + neighbors[j]);

                                // 查看相邻的细胞是否是活细胞
                                if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                    liveNeighbors += 1;
                                }
                            }
                        }
                    }

                    // 规则 1 或规则 3
                    if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                        // -1 代表这个细胞过去是活的现在死了
                        board[row][col] = -1;
                    }
                    // 规则 4
                    if (board[row][col] == 0 && liveNeighbors == 3) {
                        // 2 代表这个细胞过去是死的现在活了
                        board[row][col] = 2;
                    }
                }
            }

            // 遍历 board 得到一次更新后的状态
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (board[row][col] > 0) {
                        board[row][col] = 1;
                    } else {
                        board[row][col] = 0;
                    }
                }
            }
        }

    }
}
