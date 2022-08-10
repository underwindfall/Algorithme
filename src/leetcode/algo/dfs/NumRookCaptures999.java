package leetcode.algo.dfs;

// https://leetcode.cn/problems/available-captures-for-rook/
public class NumRookCaptures999 {
    // time O(n*m*4)
    // space O(1)
    public int numRookCaptures(char[][] board) {
        int count = 0;
        int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'R') {
                    for (int[] direction : directions) {
                        if (dfs(board, i, j, direction)) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    boolean dfs(char[][] board, int row, int col, int[] direction) {
        int i = row, j = col;
        while (inArea(board, i, j)) {
            if (board[i][j] == 'p') {
                return true;
            }
            if (board[i][j] == 'B') {
                return false;
            }

            i += direction[0];
            j += direction[1];
        }
        return false;
    }

    boolean inArea(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}
