package leetcode.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode-cn.com/problems/n-queens/solution/
//http://zxi.mytechroad.com/blog/searching/leetcode-51-n-queens/
//研究对角线的规律
// 正对角线 不会超过边界 x+y
// 副对角线x - y + n - 1 
//time O(N!)
//espace O(N)
public class NQueens51 {
    List<List<String>> sols;
    boolean[] cols;
    boolean[] diag1;
    boolean[] diag2;
    char[][] board;

    public List<List<String>> solveNQueens(int n) {
        sols = new ArrayList<>();
        board = new char[n][n];
        for (char[] b : board) {
            Arrays.fill(b, '.');
        }
        cols = new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];
        nqueens(n, 0);
        return sols;
    }

    boolean available(int x, int y, int n) {
        return !cols[x] && !diag1[x + y] && !diag2[x - y + n - 1];
    }

    // x col
    // y row
    void updateBoard(int col, int row, int n, boolean isPut) {
        cols[col] = isPut;
        diag1[col + row] = isPut;
        diag2[col - row + n - 1] = isPut;
        board[row][col] = isPut ? 'Q' : '.';
    }

    // try to put the queeen on yth row
    void nqueens(int n, int r) {
        if (r == n) {
            // found one solution, add the ans set
            List<String> list = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                list.add(new String(board[i]));
            }
            sols.add(list);
            return;
        }

        // try every col
        for (int c = 0; c < n; c++) {
            if (!available(c, r, n))
                continue;
            updateBoard(c, r, n, true);
            nqueens(n, r + 1);
            updateBoard(c, r, n, false);
        }
    }
}
