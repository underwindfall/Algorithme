package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/sudoku-solver/
//DFS + backtracking
//time O（N*M*N*M)
//espace O（N*M)
public class SolveSudoKu37 {
    public void solveSudoku(char[][] board) {
        // 三个布尔数组 表明 行, 列, 还有 3*3 的方格的数字是否被使用过
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][] boxUsed = new boolean[9][10];
        // 初始化
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int num = board[row][col] - '0';
                if (1 <= num && num <= 9) {

                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    int boxC = col / 3;
                    int boxR = row / 3;
                    int boxKey = boxR * 3 + boxC;
                    boxUsed[boxKey][num] = true;
                }
            }
        }
        // 递归尝试填充数组
        dfs(board, rowUsed, colUsed, boxUsed, 0, 0);

    }

    //true 代表已被填过
    boolean dfs(char[][] board, boolean[][] rowUsed, boolean[][] colUsed, boolean[][] boxUsed, int row, int col) {
        if (row == board.length) {
            return true;
        }

        int newC = (col + 1) % 9;
        int newR = newC == 0 ? row + 1 : row;

        if (board[row][col] != '.') {
            return dfs(board, rowUsed, colUsed, boxUsed, newR, newC);
        }
        for (int i = 1; i <= 9; i++) {
            int boxC = col / 3;
            int boxR = row / 3;
            int boxKey = boxR * 3 + boxC;
            if (!rowUsed[row][i] && !colUsed[col][i] && !boxUsed[boxKey][i]) {
                rowUsed[row][i] = true;
                colUsed[col][i] = true;
                boxUsed[boxKey][i] = true;
                board[row][col] = (char) (i + '0');
                if (dfs(board, rowUsed, colUsed, boxUsed, newR, newC)) {
                    return true;
                }
                board[row][col] = '.';
                rowUsed[row][i] = false;
                colUsed[col][i] = false;
                boxUsed[boxKey][i] = false;
            }
        }
        return false;
    }

}
