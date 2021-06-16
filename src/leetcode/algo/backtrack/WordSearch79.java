package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/word-search/
public class WordSearch79 {
    // time O( row * col * 4^N)
    // espace O(row * col)
    public boolean exist(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, word, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, int row, int col, int currentIndex, String word, boolean[][] visited) {
        if (!inArea(board, row, col)) {
            return false;
        }

        if (visited[row][col]) {
            return false;
        }

        if (currentIndex == word.length() - 1) {
            return board[row][col] == word.charAt(currentIndex);
        }

        if (board[row][col] != word.charAt(currentIndex)) {
            return false;
        }

        visited[row][col] = true;
        boolean result = dfs(board, row + 1, col, currentIndex + 1, word, visited)
                || dfs(board, row - 1, col, currentIndex + 1, word, visited)
                || dfs(board, row, col + 1, currentIndex + 1, word, visited)
                || dfs(board, row, col - 1, currentIndex + 1, word, visited);
        visited[row][col] = false;
        return result;
    }

    boolean inArea(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}
