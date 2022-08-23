
package leetcode.datastructure.array;

// https://leetcode.cn/problems/spiral-matrix-ii/
class SpiralMatrixII59 {
    public int[][] generateMatrix(int n) {
        // time O(N^2)
        // space O(N^2)
        int[][] ans = new int[n][n];
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int count = 1;
        while (count <= n * n) {
            for (int i = left; i <= right && count <= n * n; i++) {
                ans[top][i] = count;
                count++;
            }
            top++;

            for (int i = top; i <= bottom && count <= n * n; i++) {
                ans[i][right] = count;
                count++;
            }
            right--;

            for (int i = right; i >= left && count <= n * n; i--) {
                ans[bottom][i] = count;
                count++;
            }
            bottom--;

            for (int i = bottom; i >= top && count <= n * n; i--) {
                ans[i][left] = count;
                count++;
            }
            left++;
        }
        return ans;
    }
}