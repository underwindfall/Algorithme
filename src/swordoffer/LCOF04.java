package swordoffer;
// https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
public class LCOF04 {
    //time O(n + m)
    //space O(1)
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //binary search
        if (matrix.length == 0 || matrix == null) return false;
        int n = matrix.length, m = matrix[0].length;
        int row = n - 1, col = 0;
        while (row < n && row >= 0 && col < m && col >= 0) {
            int num = matrix[row][col];
            if (num == target) return true;
            else if (num < target) col++;
            else row--;
        }
        return false;
    }
}
