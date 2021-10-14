package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/surrounded-regions/
public class SurroundedRegions130 {

    // time O(row * col)
    // time O(row * col)
    //dfs
    // 1.边界附近mark所有跟O接触的 
    // 2. loop整个图， mark过的标记为O，否则原来的O标记为X
    public void solve(char[][] board) {
        //边界
        for (int i = 0; i < board[0].length; i++) {
            //第一行
            if (board[0][i] == 'O') {
                dfs(0, i, board);
            }
            //最后一行
            if (board[board.length - 1][i] == 'O')  {
                dfs(board.length - 1, i, board);
            }
        }
        
        
        for (int i = 0; i < board.length; i++) {
            //第一列
            if (board[i][0] == 'O') {
                dfs(i, 0, board);
            }
            //最后一列
            if (board[i][board[0].length - 1] == 'O') {
                dfs(i,board[0].length - 1, board);
            }
        }
        
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'M') {
                    board[i][j] = 'O';
                }
                else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

    }
    
    
    //mark 标记
    void dfs(int row, int col, char[][] board) {
        if (!inArea(row, col, board)) {
            return;
        }
        
        if (board[row][col] != 'O') {
            return;
        }
        
        board[row][col] = 'M';
        dfs(row - 1, col, board);
        dfs(row + 1, col, board);
        dfs(row, col - 1, board);
        dfs(row, col + 1, board);
    }
    
    
    boolean inArea(int row, int col, char[][] board) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}
