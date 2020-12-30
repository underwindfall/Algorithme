package leetcode.datastructure.hash;

// https://leetcode-cn.com/problems/valid-sudoku/
public class ValidSudoku36 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] line = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] grid = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (line[i][num] == true) {
                        return false;
                    } else {
                        line[i][num] = true;
                    }
                    if (col[j][num] == true) {
                        return false;
                    } else {
                        col[j][num] = true;
                    }
                    int gridNum = (i / 3) * 3 + (j / 3);

                    if (grid[gridNum][num] == true) {
                        return false;
                    } else {
                        grid[gridNum][num] = true;
                    }
                }
            }
        }
        return true;
    }
}
