package leetcode.algo.dfs;

// https://leetcode.cn/problems/transform-to-chessboard/solution/bian-wei-qipan-by-capital-worker-rlzb/
public class MoveToChessboard782 {
    public int movesToChessboard(int[][] board) {
        // 检测是否可以变为棋盘
        if (check(board)) {
            // 取出第一行和第一列，检测最小交换次数
            int[] row = board[0];
            int[] col = new int[board.length];
            for (int i = 0; i < board.length; i++) {
                col[i] = board[i][0];
            }
            return find(row) + find(col);
        } else {
            return -1;
        }
    }

    /**
     * 检测两个数组是否完全相同
     *
     * @param a 待比较数组
     * @param b 待比较数组
     * @return 数组相同返回 true，否则返回 false
     */
    private boolean isSame(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测两个数组是否完全相反
     *
     * @param a 待比较数组
     * @param b 待比较数组
     * @return 数组相反返回 true，否则返回 false
     */
    private boolean isOpposite(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] + b[i] != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测board是否可以通过行列交换变成棋盘
     *
     * @param board 棋盘
     * @return 可以变成棋盘返回 true，否则返回 false
     */
    private boolean check(int[][] board) {
        // 检测行是否只有两种模式
        // 以第一行为基准，检测其余所有的行，这些行要么和第一行完全相同，要么和第一行完全相反，否则不可能变换成棋盘
        int[] first = board[0];
        int cntSame = 1;
        int cntOpposite = 0;
        for (int i = 1; i < board.length; i++) {
            if (isSame(first, board[i])) {
                cntSame++;
            } else if (isOpposite(first, board[i])) {
                cntOpposite++;
            } else {
                return false;
            }
        }
        // 检测两种模式的数量分布是否正确
        if (cntSame == cntOpposite || cntSame == cntOpposite + 1 || cntSame == cntOpposite - 1) {
            // 行只有两种模式，且分布正确，进行列检测，
            // 行只有两种模式的情况下列必然也只有两种模式，只检测列的两种模式数量分布是否正确，只用第一个数字代表不同的两种模式进行计数
            int cnt0 = 0;
            int cnt1 = 0;
            for (int i : first) {
                if (i == 0) {
                    cnt0++;
                } else {
                    cnt1++;
                }
            }
            // 检测第一行中 0 和 1 的数量（代表两种模式的数量）是否分布正确
            if (cnt0 == cnt1 || cnt0 == cnt1 + 1 || cnt0 == cnt1 - 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 检测数据需要最少交换多少次成为有序
     *
     * @param tmp 待检测数组
     * @return 数组达到有序需要的最小交换次数
     */
    private int find(int[] tmp) {
        // 只检测 10101010…… 情况的错位数
        int start = 1;
        int error = 0;
        for (int i : tmp) {
            // 统计有多少错位
            if (i != start) {
                error++;
            }
            start = 1 - start;
        }

        // 需要交换的次数是错位的一半，因为一次交换可以消除两个错位
        // 排列为有序有两种可能，一种是 10101010……，一种是 01010101……
        // 两种情况下计算的错位数相加等于行数，所以我们只需要计算一种
        if (tmp.length % 2 == 0) {
            // 如果行数是偶数，排列为 10101010…… 或 01010101…… 都是可能的
            // 取两种情况下错位数的最小值
            return Math.min(tmp.length - error, error) >> 1;
        } else {
            // 如果行数是奇数，其实只可能排列成一种情况，这取决于 1 和 0 的数量
            // 1 比较多，必然只可能排成 10101010……，0 比较多，只能排成 01010101……
            // 不可能排列成的那种情况下计算出来的错位数是一个奇数，所以可以通过检测错位数是否为奇数来判断采取哪个情况
            if (error % 2 == 0) {
                return error >> 1;
            } else {
                return (tmp.length - error) >> 1;
            }
        }
    }
}
